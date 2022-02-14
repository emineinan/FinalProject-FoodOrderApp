package com.example.foodorderapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.foodorderapp.R
import com.example.foodorderapp.adapter.FoodListAdapter
import com.example.foodorderapp.databinding.FragmentFoodListBinding
import com.example.foodorderapp.viewmodel.FoodListViewModel

class FoodListFragment : Fragment() {
    private lateinit var binding: FragmentFoodListBinding
    private lateinit var foodListAdapter: FoodListAdapter
    private lateinit var viewModel: FoodListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_list, container, false)

        binding.foodListFragment = this
        binding.foodListToolbar = "Foods"

        viewModel.foodList.observe(viewLifecycleOwner, {
            foodListAdapter = FoodListAdapter()
            binding.foodListAdapter = foodListAdapter
            foodListAdapter.setData(it)
        })
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: FoodListViewModel by viewModels()
        viewModel = tempViewModel
    }
}