package com.example.foodorderapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.foodorderapp.repository.FoodBasketDaoRepository
import com.example.foodorderapp.repository.FoodDaoRepository

class FoodDetailViewModel : ViewModel() {
    val foodBasketDaoRepository = FoodBasketDaoRepository()

    fun addFoodsToBasket(
        foodNameBasket: String,
        foodImageNameBasket: String,
        foodPriceBasket: Int,
        foodAmountBasket: Int,
        usernameBasket: String
    ) {
        foodBasketDaoRepository.addFoodsToBasket(
            foodNameBasket,
            foodImageNameBasket,
            foodPriceBasket,
            foodAmountBasket,
            usernameBasket
        )
    }
}