package com.example.foodorderapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.foodorderapp.R
import com.example.foodorderapp.databinding.FragmentEndBinding
import com.example.foodorderapp.databinding.FragmentWelcomeBinding

class EndFragment : Fragment() {
    private lateinit var binding: FragmentEndBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_end, container, false)

        binding.buttonHome.setOnClickListener {
            val action = EndFragmentDirections.actionEndFragmentToFoodListFragment()
            Navigation.findNavController(it).navigate(action)
        }

        return binding.root
    }
}