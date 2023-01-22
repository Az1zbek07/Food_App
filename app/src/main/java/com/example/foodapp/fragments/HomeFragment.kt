package com.example.foodapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.foodapp.R
import com.example.foodapp.adapter.MealAdapter
import com.example.foodapp.database.MealDatabase
import com.example.foodapp.databinding.FragmentAddBinding
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.model.Meal

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val db by lazy { MealDatabase(requireContext()) }
    private val mealAdapter by lazy { MealAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }

        binding.rv.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = mealAdapter
        }
        mealAdapter.submitList(db.dao.getAllMeals())
        mealAdapter.onClick = {
            val bundle = bundleOf("meal" to it)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}