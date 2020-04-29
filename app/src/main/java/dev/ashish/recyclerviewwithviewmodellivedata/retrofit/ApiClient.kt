package dev.ashish.recyclerviewwithviewmodellivedata.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    companion object {

        private val interceptor = HttpLoggingInterceptor()
        private val logging = interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)

        private val httpClient = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

        private val builder = Retrofit.Builder()
            .baseUrl("https://reqres.in")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))

        fun <S> createService(serviceClass: Class<S>): S {
            val retrofit = builder.client(httpClient).build()
            return retrofit.create(serviceClass)
        }


    }

}