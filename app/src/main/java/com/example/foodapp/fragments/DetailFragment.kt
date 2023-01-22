package com.example.foodapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentDetailBinding
import com.example.foodapp.model.Meal
import com.example.foodapp.util.toBitMap

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        val meal = arguments?.getParcelable<Meal>("meal")!!

        binding.imageView.setImageBitmap(meal.image.toBitMap())
        binding.textName.text = meal.name
        binding.textRating.text = meal.rating.toString()
        binding.textDirections.text = meal.directions
        binding.textIngredients.text = meal.ingredients
        binding.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}