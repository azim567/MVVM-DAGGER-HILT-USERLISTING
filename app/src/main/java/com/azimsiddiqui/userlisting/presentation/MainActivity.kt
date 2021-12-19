package com.azimsiddiqui.userlisting.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azimsiddiqui.userlisting.presentation.viewmodel.UserViewModel
import com.azimsiddiqui.userlisting.data.model.User
import com.azimsiddiqui.userlisting.databinding.ActivityMainBinding
import com.azimsiddiqui.userlisting.di.Constants.EXTRA_USER_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity(),UserItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserViewModel by viewModels()
    private  var userList= ArrayList<User>()
    private  var filteredList= ArrayList<User>()
    private lateinit var userRecyclerAdapter: UserRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        toolbar.title="User List"

        viewModel.getUserList()
        observeLiveEvent()
        initRecyclerView()
        binding.mainScreen.ivGrid.setOnClickListener{
            changeRowToGridLayout()
        }
        binding.mainScreen.ivLinear.setOnClickListener {
            changeGridToRowLayout()
        }

        binding.etSearchProduct.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s.toString().let { query ->
                    if (query.isNotEmpty()) {
                        var filteredList = userList.filter { it.firstName.contains(query, true) }
                        if(filteredList.isEmpty()){
                            binding.mainScreen.tvNoResult.visibility=View.VISIBLE
                        }else{
                            binding.mainScreen.tvNoResult.visibility=View.GONE
                        }
                        userRecyclerAdapter.setData(filteredList)
                    }else{
                        userRecyclerAdapter.setData(userList)
                    }

                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

    }


    private fun changeGridToRowLayout() {
        userRecyclerAdapter.setLayoutType(false)
        binding.mainScreen.rvUserList.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = userRecyclerAdapter
        }
    }

    private fun changeRowToGridLayout() {
        userRecyclerAdapter.setLayoutType(true)
        binding.mainScreen.rvUserList.apply {
            layoutManager=GridLayoutManager(this@MainActivity,2)
            adapter=userRecyclerAdapter
        }
    }

    private fun initRecyclerView() {
        binding.mainScreen.rvUserList.apply {
            layoutManager= LinearLayoutManager(this@MainActivity,RecyclerView.VERTICAL,false)
            hasFixedSize()
        }
    }

    private fun observeLiveEvent() {
        binding.mainScreen.pbLoading.visibility=View.VISIBLE
        viewModel.userListLiveData.observe(this, { userResponse ->
            userResponse?.let {
                userList.addAll(it)
                userRecyclerAdapter=UserRecyclerAdapter(this)
                binding.mainScreen.rvUserList.adapter=userRecyclerAdapter
                if(userList.isEmpty()){
                    binding.mainScreen.tvNoResult.visibility=View.VISIBLE
                }
                else{
                    binding.mainScreen.tvNoResult.visibility=View.GONE
                }
                binding.mainScreen.pbLoading.visibility=View.GONE
                userRecyclerAdapter.setData(userList)
            }

        })
    }

    override fun onClick(id: String) {
    val intent=Intent(this,UserDetailActivity::class.java)
        intent.putExtra(EXTRA_USER_ID,id)
        startActivity(intent)
    }
}