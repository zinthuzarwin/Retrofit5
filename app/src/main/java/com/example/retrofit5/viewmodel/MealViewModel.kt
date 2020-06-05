package com.example.retrofit5.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit5.api.MealApi
import com.example.retrofit5.model.Food
import com.example.retrofit5.model.Meal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel : ViewModel() {

    var mealList: MutableLiveData<List<Meal>> = MutableLiveData()
    var mealLoadError: MutableLiveData<Boolean> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getMealList(): LiveData<List<Meal>> = mealList
    fun getError(): LiveData<Boolean> = mealLoadError
    fun getLoading(): LiveData<Boolean> = loading

    private val mealApi: MealApi = MealApi()

    fun loadFood(category: String) {
        loading.value = true
        val apiCall = mealApi.getFood(category)

        apiCall.enqueue(object : Callback<Food> {
            override fun onFailure(call: Call<Food>, t: Throwable) {
                Log.i("Error>>>>>>>", "Loading Fail")
                mealLoadError.value = true
                loading.value = false
            }

            override fun onResponse(call: Call<Food>, response: Response<Food>) {

                response.isSuccessful.let {
                    loading.value = false
                    val beefList: List<Meal> = response.body()?.meals ?: emptyList()
                    Log.d("meallist>>>",beefList.toString())
                    Log.d("responseBody>>>",response.body().toString())
                    mealList.value = beefList
                }
            }

        })
    }

}