package com.example.foodorderapp.model.foodbasket

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodBasketResponse(
    @SerializedName("sepet_yemekler")
    @Expose var foodsBasket: List<FoodBasket>,
    @SerializedName("success")
    @Expose var success: Int
)