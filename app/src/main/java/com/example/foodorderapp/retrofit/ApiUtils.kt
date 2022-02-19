package com.example.foodorderapp.retrofit

class ApiUtils {
    companion object {
        private const val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getFoodDaoInterface(): FoodDaoInterface {
            return RetrofitClient.getClient(BASE_URL).create(FoodDaoInterface::class.java)
        }

        fun getFoodBasketDaoInterface(): FoodBasketDaoInterface {
            return RetrofitClient.getClient(BASE_URL).create(FoodBasketDaoInterface::class.java)
        }
    }
}