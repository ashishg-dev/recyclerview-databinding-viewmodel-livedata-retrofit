package dev.ashish.recyclerviewwithviewmodellivedata.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.ashish.recyclerviewwithviewmodellivedata.R
import dev.ashish.recyclerviewwithviewmodellivedata.databinding.UserDetailsBinding
import dev.ashish.recyclerviewwithviewmodellivedata.viewmodel.UserViewModel

class UserDetailsAdapter(val context: Context, var data: ArrayList<UserViewModel>) :
    RecyclerView.Adapter<UserDetailsAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val userDetailsBinding: UserDetailsBinding) :
        RecyclerView.ViewHolder(userDetailsBinding.root) {
        fun bind(item: UserViewModel) {
            userDetailsBinding.userModel = item
            userDetailsBinding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: UserDetailsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.lsv_user_details,
            parent, false
        )
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

}