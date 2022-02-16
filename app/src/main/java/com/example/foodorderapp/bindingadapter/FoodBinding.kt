package com.example.foodorderapp.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, imageName: String) {
    val url = "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"
    Picasso.get().load(url).into(imageView)
}

