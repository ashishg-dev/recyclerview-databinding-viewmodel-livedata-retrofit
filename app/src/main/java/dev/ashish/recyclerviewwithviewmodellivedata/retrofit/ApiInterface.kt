package dev.ashish.recyclerviewwithviewmodellivedata.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiInterface {

    @GET("/api/users")
    fun getUserDetails(): Call<ResponseBody>
}