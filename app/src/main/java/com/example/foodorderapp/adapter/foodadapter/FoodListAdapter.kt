package com.example.foodorderapp.adapter.foodadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderapp.databinding.FoodRowItemBinding
import com.example.foodorderapp.fragments.FoodListFragmentDirections
import com.example.foodorderapp.model.food.Food

class FoodListAdapter : RecyclerView.Adapter<FoodListAdapter.MyViewHolder>() {
    private var foodList = emptyList<Food>()

    class MyViewHolder(val binding: FoodRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(food: Food) {
            binding.food = food
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FoodRowItemBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentFood = foodList[position]
        holder.bind(currentFood)

        holder.binding.cardViewFood.setOnClickListener {
            val action =
                FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment(currentFood)
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.textViewRating.text = (1 until 6).random().toDouble().toString()
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun setData(newData: List<Food>) {
        val foodListDiffUtil = FoodListDiffUtil(foodList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(foodListDiffUtil)
        foodList = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}