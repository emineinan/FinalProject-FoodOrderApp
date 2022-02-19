package com.example.foodorderapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.foodorderapp.R
import com.example.foodorderapp.databinding.FragmentFoodDetailBinding
import com.example.foodorderapp.viewmodel.FoodDetailViewModel

class FoodDetailFragment : Fragment() {
    private lateinit var binding: FragmentFoodDetailBinding
    private lateinit var viewModel: FoodDetailViewModel
    private val args: FoodDetailFragmentArgs by navArgs()
    private var quantity = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_detail, container, false)
        val food = args.food
        binding.food = food

        binding.buttonAddToBasket.setOnClickListener {
            viewModel.addFoodsToBasket(food.foodName, food.foodImageName, food.foodPrice, quantity, "e_inan")

            val action =
                FoodDetailFragmentDirections.actionFoodDetailFragmentToFoodBasketFragment(quantity)
            Navigation.findNavController(it).navigate(action)
        }

        binding.imageViewIncrease.setOnClickListener {
            quantity++
            binding.textViewQuantity.text = quantity.toString()
            binding.textViewFoodPriceDetail.text = (quantity * food.foodPrice).toString() + " ₺"
        }

        binding.imageViewDecrease.setOnClickListener {
            if (quantity > 1) {
                quantity--
                binding.imageViewDecrease.isClickable = true
                binding.textViewQuantity.text = quantity.toString()
                binding.textViewFoodPriceDetail.text = (quantity * food.foodPrice).toString() + " ₺"
            } else {
                Toast.makeText(
                    requireContext(),
                    "Adet sayısı 1'den küçük olamaz.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: FoodDetailViewModel by viewModels()
        viewModel = tempViewModel
    }
}