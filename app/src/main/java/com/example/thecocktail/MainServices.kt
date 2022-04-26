package com.example.thecocktail

import retrofit2.Call
import retrofit2.http.GET

interface MainServices {
    @GET("api/json/v1/1/search.php?f=a")
    fun getList(): Call<CocktailDetailResponse>
}