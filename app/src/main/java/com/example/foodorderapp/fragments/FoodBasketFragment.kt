package com.example.foodorderapp.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.foodorderapp.R
import com.example.foodorderapp.adapter.foodbasketadapter.FoodBasketAdapter
import com.example.foodorderapp.databinding.FragmentFoodBasketBinding
import com.example.foodorderapp.viewmodel.FoodBasketViewModel

class FoodBasketFragment : Fragment() {
    private lateinit var binding: FragmentFoodBasketBinding
    private lateinit var foodBasketAdapter: FoodBasketAdapter
    private lateinit var viewModel: FoodBasketViewModel
    private val args: FoodBasketFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_basket, container, false)

        binding.foodBasketFragment = this
        binding.foodBasketToolbar = "Sepetim"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarFoodBasket)

        setAdapter()

        viewModel.loadFoodsFromBasket("e_inan")

        getAllFoodsFromBasket()

        clearAllFoodsFromBasket()

        binding.buttonOrder.setOnClickListener {
            val action = FoodBasketFragmentDirections.actionFoodBasketFragmentToEndFragment()
            Navigation.findNavController(it).navigate(action)
        }

        return binding.root
    }

    private fun clearAllFoodsFromBasket() {
        foodBasketAdapter.clearAll()
    }

    private fun getAllFoodsFromBasket() {
        viewModel.foodBasketList.observe(viewLifecycleOwner, {
            foodBasketAdapter.setData(it)
        })
    }

    private fun setAdapter() {
        foodBasketAdapter = FoodBasketAdapter(requireContext(), viewModel, args)
        binding.foodBasketAdapter = foodBasketAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val tempViewModel: FoodBasketViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_delete) {
            clearAllFoodsFromBasket()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        getAllFoodsFromBasket()
    }
}