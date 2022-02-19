package com.example.foodorderapp.repository

import androidx.lifecycle.MutableLiveData
import com.example.foodorderapp.model.food.Food
import com.example.foodorderapp.model.food.FoodResponse
import com.example.foodorderapp.retrofit.ApiUtils
import com.example.foodorderapp.retrofit.FoodDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodDaoRepository {
    var foodList: MutableLiveData<List<Food>> = MutableLiveData()
    var foodDaoInterface: FoodDaoInterface = ApiUtils.getFoodDaoInterface()

    fun getFoods(): MutableLiveData<List<Food>> {
        return foodList
    }

    fun getAllFoods() {
        foodDaoInterface.allFoods().enqueue(object : Callback<FoodResponse> {
            override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                val foods = response.body().foods
                foodList.value = foods
            }

            override fun onFailure(call: Call<FoodResponse>?, t: Throwable?) {
            }
        })
    }
}