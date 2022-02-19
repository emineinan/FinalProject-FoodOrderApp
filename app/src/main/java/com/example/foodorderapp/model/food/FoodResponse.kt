package com.example.foodorderapp.model.food

import com.example.foodorderapp.model.food.Food
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @SerializedName("yemekler")
    @Expose var foods: List<Food>,
    @SerializedName("success")
    @Expose var success: Int
)