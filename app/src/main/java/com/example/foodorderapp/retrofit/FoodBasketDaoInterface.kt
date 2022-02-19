package com.example.foodorderapp.retrofit

import com.example.foodorderapp.model.foodbasket.FoodBasketResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface FoodBasketDaoInterface {
    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun allFoodsInBasket(@Field("kullanici_adi") usernameBasket: String): Call<FoodBasketResponse>

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun addFoodsToBasket(
        @Field("yemek_adi") foodNameBasket: String,
        @Field("yemek_resim_adi") foodImageNameBasket: String,
        @Field("yemek_fiyat") foodPriceBasket: Int,
        @Field("yemek_siparis_adet") foodAmountBasket:Int,
        @Field("kullanici_adi") usernameBasket: String
    ): Call<FoodBasketResponse>

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun deleteFoodFromBasket(
        @Field("sepet_yemek_id") foodIdBasket: Int,
        @Field("kullanici_adi") usernameBasket: String
    ): Call<FoodBasketResponse>
}