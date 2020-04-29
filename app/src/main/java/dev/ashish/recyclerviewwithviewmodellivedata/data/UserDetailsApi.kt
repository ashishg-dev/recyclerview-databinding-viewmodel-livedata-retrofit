package dev.ashish.recyclerviewwithviewmodellivedata.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import dev.ashish.recyclerviewwithviewmodellivedata.retrofit.ApiClient
import dev.ashish.recyclerviewwithviewmodellivedata.retrofit.ApiInterface
import dev.ashish.recyclerviewwithviewmodellivedata.viewmodel.UserViewModel
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailsApi {

    private var arrayListMutableLiveData = MutableLiveData<ArrayList<UserViewModel>>()
    private var arrayListUserViewModel = ArrayList<UserViewModel>()


    public fun getUserList(): MutableLiveData<ArrayList<UserViewModel>> {

        val service = ApiClient.createService(ApiInterface::class.java)
        val call = service.getUserDetails()

        call.enqueue(object : Callback<ResponseBody> {

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                try {
                    Log.d("UserDetailsApi","onResponse")
                    if (response!!.isSuccessful) {

                        val jsonObject = JSONObject(response.body().string())
                        val jsonDataArray = jsonObject.getJSONArray("data")
                        for (jsonIndex in 0 until jsonDataArray.length()) {
                            val singleObjects = jsonDataArray.getJSONObject(jsonIndex)

                            arrayListUserViewModel.add(
                                UserViewModel(
                                    singleObjects.getString("email"),
                                    singleObjects.getString("first_name"),
                                    singleObjects.getString("last_name"),
                                    singleObjects.getString("first_name") + " " +
                                            singleObjects.getString("last_name"),
                                    singleObjects.getString("avatar")
                                )
                            )


                        }

                        arrayListMutableLiveData.value = arrayListUserViewModel

                    } else {
                        println("Something went wrong")
                    }

                } catch (e: Exception) {
                    println(e.message)
                }
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                println(t!!.message)
            }

        })

        return arrayListMutableLiveData
    }


}