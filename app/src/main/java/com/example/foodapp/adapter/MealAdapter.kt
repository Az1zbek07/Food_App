package com.example.foodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.databinding.ItemLayoutBinding
import com.example.foodapp.model.Meal
import com.example.foodapp.util.toBitMap

class MealAdapter: ListAdapter<Meal, MealAdapter.RvViewHolder>(DiffCallback()) {
    lateinit var onClick: (Meal) -> Unit

    private class DiffCallback: DiffUtil.ItemCallback<Meal>(){
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        return RvViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RvViewHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(meal: Meal){
            binding.textName.text = meal.name
            binding.imageView.setImageBitmap(meal.image.toBitMap())
            itemView.setOnClickListener{
                onClick(meal)
            }
        }
    }
}