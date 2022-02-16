package com.example.foodorderapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.foodorderapp.repository.FoodDaoRepository

class FoodDetailViewModel: ViewModel() {
    val foodDaoRepository = FoodDaoRepository()
}