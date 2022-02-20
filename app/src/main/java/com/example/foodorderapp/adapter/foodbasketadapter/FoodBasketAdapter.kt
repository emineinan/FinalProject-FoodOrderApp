package com.example.foodorderapp.adapter.foodbasketadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderapp.databinding.FoodBasketRowItemBinding
import com.example.foodorderapp.fragments.FoodBasketFragmentArgs
import com.example.foodorderapp.fragments.FoodBasketFragmentDirections
import com.example.foodorderapp.model.foodbasket.FoodBasket
import com.example.foodorderapp.viewmodel.FoodBasketViewModel
import com.google.android.material.snackbar.Snackbar

class FoodBasketAdapter(
    var context: Context,
    var viewModel: FoodBasketViewModel,
    var args: FoodBasketFragmentArgs,
    var onItemTrashClicked: ((item: FoodBasket) -> Unit?)? = null
) : RecyclerView.Adapter<FoodBasketAdapter.MyViewHolder>() {
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
        var quantity = args.foodQuantity

        holder.binding.textViewQuantity.text = quantity.toString()
        holder.binding.textViewFoodPriceBasket.text =
            (quantity * currentFood.foodPriceBasket).toString() + " ₺"

        holder.binding.imageViewIncrease.setOnClickListener {
            quantity++
            holder.binding.textViewQuantity.text = quantity.toString()
            holder.binding.textViewFoodPriceBasket.text =
                (quantity * currentFood.foodPriceBasket).toString() + " ₺"
        }

        holder.binding.imageViewDecrease.setOnClickListener {
            if (quantity > 1) {
                holder.binding.imageViewDecrease.isClickable = true
                quantity--
                holder.binding.textViewQuantity.text = quantity.toString()
                holder.binding.textViewFoodPriceBasket.text =
                    (quantity * currentFood.foodPriceBasket).toString() + " ₺"

            } else {
                Toast.makeText(
                    context,
                    "Adet sayısı 1'den küçük olamaz.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        holder.binding.imageViewDelete.setOnClickListener {
            if (foodBasketList.size != 1) {
                onItemTrashClicked?.invoke(currentFood)
            } else {
                viewModel.deleteFoodFromBasket(currentFood.foodIdBasket, "e_inan")
                clearAll()
                Snackbar.make(
                    it,
                    "Sepetiniz boş, lütfen devam etmek için bir ürün ekleyin.",
                    Snackbar.LENGTH_LONG
                ).setAction("TAMAM") {
                }.show()
            }
        }
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

    fun clearAll() {
        foodBasketList = emptyList()
        notifyDataSetChanged()
    }
}