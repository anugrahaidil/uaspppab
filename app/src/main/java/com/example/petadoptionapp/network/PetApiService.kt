package com.example.petadoptionapp.network

import com.example.petadoptionapp.model.Pet
import retrofit2.http.GET

interface PetApiService {

    // Mendapatkan daftar semua pets
    @GET("LIcJU/pets")
    suspend fun getPets(): List<Pet>
    
}
