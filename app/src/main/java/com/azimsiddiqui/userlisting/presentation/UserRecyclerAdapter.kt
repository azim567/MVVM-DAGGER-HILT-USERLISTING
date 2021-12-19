package com.azimsiddiqui.userlisting.presentation


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azimsiddiqui.userlisting.R
import com.azimsiddiqui.userlisting.data.model.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_user_list_grid_row.view.*

class UserRecyclerAdapter(private var listener: UserItemClickListener) : RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>() {

    private  var userList=ArrayList<User>()
    private var isGrid=false
    inner class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(user: User) {
            Glide.with(itemView.context)
                .load(user.picture)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.dummy_user)
                .error(R.drawable.dummy_user)
                .into(itemView.user_picture)

            itemView.name.text = "${user.title.capitalize()} ${user.firstName} ${user.lastName} "
            itemView.parent_layout.setOnClickListener {
                listener.onClick(user.id)
            }
        }


    }

    fun setLayoutType(isGrid:Boolean){
        this.isGrid=isGrid
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var layout=if(isGrid) R.layout.item_user_list_grid_row else R.layout.item_user_list_horizontal
        val layoutInflater=LayoutInflater.from(parent.context).inflate(layout,parent,false)
       return UserViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
       return userList.size
    }

    fun setData(list:List<User>){
        userList.clear()
        userList.addAll(list)
        notifyDataSetChanged()
    }
}