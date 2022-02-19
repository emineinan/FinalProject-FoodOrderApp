package com.example.foodorderapp.adapter.foodbasketadapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderapp.databinding.FoodBasketRowItemBinding
import com.example.foodorderapp.model.foodbasket.FoodBasket

class FoodBasketAdapter : RecyclerView.Adapter<FoodBasketAdapter.MyViewHolder>() {
    private var foodBasketList = emptyList<FoodBasket>()

    class MyViewHolder(val binding: FoodBasketRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(foodBasket: FoodBasket) {
            binding.foodBasket = foodBasket
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FoodBasketRowItemBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentFood = foodBasketList[position]
        holder.bind(currentFood)

        // Miktar arttıkça fiyat artırma işlemi
    }

    override fun getItemCount(): Int {
        return foodBasketList.size
    }

    fun setData(newData: List<FoodBasket>) {
        val foodBasketDiffUtil = FoodBasketDiffUtil(foodBasketList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(foodBasketDiffUtil)
        foodBasketList = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}