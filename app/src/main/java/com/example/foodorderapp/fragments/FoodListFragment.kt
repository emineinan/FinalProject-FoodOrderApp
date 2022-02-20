package com.example.foodorderapp.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.foodorderapp.R
import com.example.foodorderapp.adapter.foodadapter.FoodListAdapter
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
        binding.foodListToolbar = "Anasayfa"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarFoodList)

        viewModel.foodList.observe(viewLifecycleOwner, {
            foodListAdapter = FoodListAdapter()
            binding.foodListAdapter = foodListAdapter
            foodListAdapter.setData(it)
        })
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val tempViewModel: FoodListViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.basket_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_basket) {
            val action =
                FoodListFragmentDirections.actionFoodListFragmentToFoodBasketFragment()
            Navigation.findNavController(binding.root).navigate(action)
        }
        return super.onOptionsItemSelected(item)
    }
}