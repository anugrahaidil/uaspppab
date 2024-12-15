package com.example.petadoptionapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petadoptionapp.R
import com.example.petadoptionapp.adapter.PetAdapter
import com.example.petadoptionapp.databinding.FragmentHomeBinding
import com.example.petadoptionapp.viewmodel.PetViewModel

class HomeFragment : Fragment() {
    private lateinit var viewModel: PetViewModel
    private lateinit var adapter: PetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(PetViewModel::class.java)

        adapter = PetAdapter(emptyList()) { pet ->
            viewModel.toggleFavorite(pet)
        }
        binding.recyclerViewPets.adapter = adapter

        // Observe pet data
        viewModel.pets.observe(viewLifecycleOwner) { pets ->
            adapter.updateData(pets)
        }

        // Handle category button clicks
        binding.buttonDogs.setOnClickListener {
            viewModel.fetchPetsByCategory("dogs")
        }
        binding.buttonCats.setOnClickListener {
            viewModel.fetchPetsByCategory("cats")
        }
        binding.buttonParrot.setOnClickListener {
            viewModel.fetchPetsByCategory("parrot")
        }
        binding.buttonRabbit.setOnClickListener {
            viewModel.fetchPetsByCategory("rabbit")
        }

        return binding.root
    }
}

