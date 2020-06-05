package com.example.retrofit5.api

import com.example.retrofit5.model.Food
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealApi {

    private val mealInterface: Meal_Interface

    companion object {
        const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mealInterface = retrofit.create(Meal_Interface::class.java)

    }

    fun getFood(category: String): Call<Food> {
        return mealInterface.getFood(category)
    }

}