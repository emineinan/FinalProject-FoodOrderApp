package com.example.foodorderapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @SerializedName("yemekler")
    @Expose var foods: List<Food>,
    @SerializedName("success")
    @Expose var success: Int
)