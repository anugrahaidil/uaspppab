package com.example.petadoptionapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petadoptionapp.R
import com.example.petadoptionapp.databinding.ItemPetBinding
import com.example.petadoptionapp.model.Pet

class PetAdapter(
    private var pets: List<Pet>,
    private val onFavoriteClick: (Pet) -> Unit
) : RecyclerView.Adapter<PetAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemPetBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pet = pets[position]
        holder.binding.textViewName.text = pet.name
        holder.binding.textViewBreed.text = pet.breed
        Glide.with(holder.itemView.context).load(pet.imageUrl).into(holder.binding.imageViewPet)

        holder.binding.imageViewFavorite.setImageResource(
            if (pet.isFavorite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
        )
        holder.binding.imageViewFavorite.setOnClickListener {
            onFavoriteClick(pet)
        }
    }

    override fun getItemCount() = pets.size

    fun updateData(newPets: List<Pet>) {
        pets = newPets
        notifyDataSetChanged()
    }
}


