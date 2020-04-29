package dev.ashish.recyclerviewwithviewmodellivedata.viewmodel

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.picasso.Picasso
import dev.ashish.recyclerviewwithviewmodellivedata.data.UserDetailsApi

class UserViewModel(
    val email: String,
    val fistName: String,
    val lastName: String,
    val fullName: String,
    val avatar: String
) : ViewModel() {

    constructor() : this("", "", "", "", "") {
        Log.d("UserViewModel","constructor")
        val userDetailsApi = UserDetailsApi()
        arrayListMutableLiveData = userDetailsApi.getUserList()

    }


    private lateinit var arrayListMutableLiveData: MutableLiveData<ArrayList<UserViewModel>>

    public fun getUserListLiveData(): MutableLiveData<ArrayList<UserViewModel>> {
        Log.d("UserViewModel","getUserListLiveData")
        return arrayListMutableLiveData
    }

}

@BindingAdapter("app:setImage")
fun setImage(view: ImageView, url: String) {

    Picasso.get().load(url).into(view)

}