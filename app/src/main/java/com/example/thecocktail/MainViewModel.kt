package com.example.thecocktail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecocktail.Utils.getHttpClientBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel: ViewModel() {
    var cocktailDetailResponse = MutableLiveData<CocktailDetailResponse>()
    var BASE_URL = "https://www.thecocktaildb.com/"
    lateinit var httpClient: OkHttpClient
    lateinit var retrofit: Retrofit
    fun getData(){
        httpClient = getHttpClientBuilder()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.newBuilder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val api = retrofit.create(MainServices::class.java)
        val call = api.getList()

        call.enqueue(object : Callback<CocktailDetailResponse> {
            override fun onFailure(call: Call<CocktailDetailResponse>, t: Throwable) {
                //Toast.makeText(this ,"On failure",Toast.LENGTH_SHORT).show()
                Log.i("h", "on fail" + t.toString())
            }

            override fun onResponse(call: Call<CocktailDetailResponse>, response: retrofit2.Response<CocktailDetailResponse>) {
                Log.i("d", response.body().toString())
//                progressBar.visibility= View.GONE
                cocktailDetailResponse.postValue(response.body())
            }
        })
    }
}