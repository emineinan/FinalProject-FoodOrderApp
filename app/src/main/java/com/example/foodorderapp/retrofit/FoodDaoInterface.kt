package com.example.foodorderapp.retrofit

import com.example.foodorderapp.model.food.FoodResponse
import retrofit2.Call
import retrofit2.http.GET

interface FoodDaoInterface {
    @GET("yemekler/tumYemekleriGetir.php")
    fun allFoods(): Call<FoodResponse>
}