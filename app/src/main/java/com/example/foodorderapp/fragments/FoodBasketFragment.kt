package com.example.foodorderapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.foodorderapp.R
import com.example.foodorderapp.adapter.foodbasketadapter.FoodBasketAdapter
import com.example.foodorderapp.databinding.FragmentFoodBasketBinding
import com.example.foodorderapp.viewmodel.FoodBasketViewModel

class FoodBasketFragment : Fragment() {
    private lateinit var binding: FragmentFoodBasketBinding
    private lateinit var foodBasketAdapter: FoodBasketAdapter
    private lateinit var viewModel: FoodBasketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_basket, container, false)

        binding.foodBasketFragment = this
        binding.foodBasketToolbar = "Sepetim"


        viewModel.loadFoodsFromBasket("e_inan")
        viewModel.foodBasketList.observe(viewLifecycleOwner, {
            foodBasketAdapter = FoodBasketAdapter(requireContext(), viewModel)
            binding.foodBasketAdapter = foodBasketAdapter
            foodBasketAdapter.setData(it)
            Log.e("HATA", it.size.toString())
        })

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: FoodBasketViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadFoodsFromBasket("e_inan")
    }

}