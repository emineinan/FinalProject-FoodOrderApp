package com.example.foodorderapp.model.foodbasket

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FoodBasket(
    @SerializedName("sepet_yemek_id")
    @Expose
    var foodIdBasket: Int,
    @SerializedName("yemek_adi")
    @Expose
    var foodNameBasket: String,
    @SerializedName("yemek_resim_adi")
    @Expose
    var foodImageNameBasket: String,
    @SerializedName("yemek_fiyat")
    @Expose
    var foodPriceBasket: Int,
    @SerializedName("yemek_siparis_adet")
    @Expose
    var foodAmountBasket: Int,
    @SerializedName("kullanici_adi")
    @Expose
    var userNameBasket: String
) : Serializable