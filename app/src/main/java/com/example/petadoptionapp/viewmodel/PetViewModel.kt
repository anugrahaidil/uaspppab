package com.example.petadoptionapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petadoptionapp.database.AppDatabase
import com.example.petadoptionapp.model.Pet
import com.example.petadoptionapp.network.ApiService
import com.example.petadoptionapp.network.PetApiService
import com.example.petadoptionapp.network.RetrofitClient
import com.example.petadoptionapp.repository.PetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PetViewModel(application: Application) : AndroidViewModel(application) {
    private val petRepository: PetRepository
    private val _pets = MutableLiveData<List<Pet>>()
    val pets: LiveData<List<Pet>> get() = _pets

    init {
        val db = AppDatabase.getDatabase(application)
        petRepository = PetRepository(
            api = RetrofitClient.retrofit.create(PetApiService::class.java),
            favoritePetDao = db.favoritePetDao()
        )
        fetchPets()
    }

    private fun fetchPets() {
        viewModelScope.launch {
            _pets.value = petRepository.getPets()
        }
    }

    fun fetchPetsByCategory(category: String) {
        viewModelScope.launch {
            _pets.value = petRepository.getPetsByCategory(category)
        }
    }

    fun toggleFavorite(pet: Pet) {
        viewModelScope.launch {
            petRepository.toggleFavorite(pet)
            fetchPets() // Perbarui daftar setelah toggle favorit
        }
    }
}

