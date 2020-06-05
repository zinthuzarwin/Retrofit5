package com.example.retrofit5.api

import com.example.retrofit5.model.Food
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Meal_Interface {

    @GET("filter.php")
    fun getFood(@Query("c") search: String):Call<Food>

}