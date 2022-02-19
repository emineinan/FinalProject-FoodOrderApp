package com.example.foodorderapp.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.foodorderapp.model.foodbasket.FoodBasket
import com.example.foodorderapp.model.foodbasket.FoodBasketResponse
import com.example.foodorderapp.retrofit.ApiUtils
import com.example.foodorderapp.retrofit.FoodBasketDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodBasketDaoRepository {
    var foodBasketList: MutableLiveData<List<FoodBasket>> = MutableLiveData()
    var foodBasketDaoInterface: FoodBasketDaoInterface = ApiUtils.getFoodBasketDaoInterface()
    //private val context: Context? = null

    fun getFoodsBasket(): MutableLiveData<List<FoodBasket>> {
        return foodBasketList
    }

    fun getAllFoodsFromBasket(usernameBasket: String) {
        foodBasketDaoInterface.allFoodsInBasket(usernameBasket)
            .enqueue(object : Callback<FoodBasketResponse> {
                override fun onResponse(
                    call: Call<FoodBasketResponse>?,
                    response: Response<FoodBasketResponse>
                ) {
                    val foodsBasket = response.body().foodsBasket
                    foodBasketList.value = foodsBasket
                }

                override fun onFailure(call: Call<FoodBasketResponse>?, t: Throwable?) {
                }
            })
    }

    fun deleteFoodFromBasket(foodIdBasket: Int, usernameBasket: String) {
        foodBasketDaoInterface.deleteFoodFromBasket(foodIdBasket, usernameBasket)
            .enqueue(object : Callback<FoodBasketResponse> {
                override fun onResponse(
                    call: Call<FoodBasketResponse>,
                    response: Response<FoodBasketResponse>
                ) {
                    //toast mesaj ya da snackbar ekle
                }

                override fun onFailure(call: Call<FoodBasketResponse>?, t: Throwable?) {
                }
            })
    }

    fun addFoodsToBasket(
        foodNameBasket: String,
        foodImageNameBasket: String,
        foodPriceBasket: Int,
        foodAmountBasket:Int,
        usernameBasket: String
    ) {
        foodBasketDaoInterface.addFoodsToBasket(
            foodNameBasket, foodImageNameBasket,
            foodPriceBasket, foodAmountBasket, usernameBasket
        )
            .enqueue(object : Callback<FoodBasketResponse> {
                override fun onResponse(
                    call: Call<FoodBasketResponse>,
                    response: Response<FoodBasketResponse>
                ) {
                }

                override fun onFailure(call: Call<FoodBasketResponse>?, t: Throwable?) {
                }
            })
    }
}