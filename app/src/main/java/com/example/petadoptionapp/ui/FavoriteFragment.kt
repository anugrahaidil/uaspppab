package com.example.petadoptionapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.petadoptionapp.R
import com.example.petadoptionapp.adapter.PetAdapter
import com.example.petadoptionapp.databinding.FragmentFavoriteBinding
import com.example.petadoptionapp.viewmodel.PetViewModel

class FavoriteFragment : Fragment() {
    private lateinit var viewModel: PetViewModel
    private lateinit var adapter: PetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(PetViewModel::class.java)

        adapter = PetAdapter(emptyList()) { pet ->
            viewModel.toggleFavorite(pet)
        }
        binding.recyclerViewFavorites.adapter = adapter

        // Observe pet data
        viewModel.pets.observe(viewLifecycleOwner) { pets ->
            val favoritePets = pets.filter { it.isFavorite }
            adapter.updateData(favoritePets)
        }

        return binding.root
    }
}
