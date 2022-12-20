package com.azimsiddiqui.userlisting.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.azimsiddiqui.userlisting.presentation.viewmodel.UserViewModel
import com.azimsiddiqui.userlisting.data.model.UserDetailResponse
import com.azimsiddiqui.userlisting.databinding.ActivityUserDetailBinding
import com.azimsiddiqui.userlisting.di.Constants.EXTRA_USER_ID
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_user_list_grid_row.*
import android.net.Uri

import android.content.Intent


@AndroidEntryPoint
class UserDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailBinding
    private val viewModel: UserViewModel by viewModels()
    private lateinit var address: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.extras?.get(EXTRA_USER_ID) as String
        viewModel.getUserDetail(id)

        observeLiveEvent()


        binding.tvLocate.setOnClickListener {
            val url = "https://www.google.com/maps/search/?api=1&query=$address"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun observeLiveEvent() {
        binding.pbLoading.visibility = View.VISIBLE
        viewModel.userDetailsLiveData.observe(this, Observer { userDetail ->
            when(userDetail) {
                is ApiResult.Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                }
                is ApiResult.Success -> {
                    binding.pbLoading.visibility = View.GONE
                    userDetail.data?.let {
                        setupUI(it)
                    }
                }
                is ApiResult.Error -> {
                    binding.pbLoading.visibility = View.GONE
                }
            }
        })
    }

    private fun setupUI(user: UserDetailResponse) {
        with(binding) {
            Glide.with(this@UserDetailActivity)
                .load(user.picture)
                .apply(RequestOptions.circleCropTransform())
                .into(ivPicture)

            tvName.text = user.title.capitalize() + " " + user.firstName + " " + user.lastName
            txtGender.text = user.gender
            txtDob.text = user.dateOfBirth.split("T")[0]
            txtEmail.text = user.email
            txtPhone.text = user.phone
            address = "${user.location.street}, ${user.location.city}, ${user.location.state}, ${user.location.country}"
            txtAddress.text = address
        }
    }
}