package com.example.foodapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.foodapp.R
import com.example.foodapp.database.MealDatabase
import com.example.foodapp.databinding.FragmentAddBinding
import com.example.foodapp.model.Meal
import com.example.foodapp.util.toByteArray

class AddFragment : Fragment(R.layout.fragment_add) {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private val db by lazy { MealDatabase(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddBinding.bind(view)

        binding.addPhoto.setOnClickListener {
            binding.addPhoto.setBackgroundResource(0)
            imageLauncher.launch("image/*")
        }

        binding.btnSave.setOnClickListener {
            val name = binding.editName.text.toString().trim()
            val rating = binding.editRating.text.toString().trim().toDouble()
            val ingredients = binding.editIngredients.text.toString().trim()
            val directions = binding.editDirections.text.toString().trim()
            val image = binding.addPhoto

            if (name.isNotBlank() && rating < 5 && ingredients.isNotBlank() && directions.isNotBlank()){
                val meal = Meal(image = image.toByteArray(), name = name, rating = rating, ingredients = ingredients, directions = directions)
                db.dao.saveMeal(meal)
                Toast.makeText(requireContext(), "Saved Successfully", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }else{
                Toast.makeText(requireContext(), "Please enter values", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val imageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){
        it?.let { uri ->
            binding.addPhoto.setImageURI(uri)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}