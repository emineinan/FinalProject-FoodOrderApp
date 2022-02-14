package com.example.foodorderapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderapp.databinding.FoodRowItemBinding
import com.example.foodorderapp.model.Food
import com.example.foodorderapp.util.FoodListDiffUtil
import com.squareup.picasso.Picasso

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

        // Show food image with Picasso
        val imageName = currentFood.foodImageName
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"
        Picasso.get().load(url).into(holder.binding.imageViewFood)
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