package com.example.petadoptionapp.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Query

interface ApiService {

    // Mendapatkan semua data hewan
    @GET("LIcJU/pets")
    suspend fun getAllPets(): List<Map<String, Any>>

    // Mendapatkan data berdasarkan kategori
    @GET("LIcJU/pets")
    suspend fun getPetsByCategory(
        @Query("category") category: String
    ): List<Map<String, Any>>

    // Menambahkan data baru
    @POST("LIcJU/pets")
    suspend fun addPet(
        @Body pet: Map<String, Any>
    ): Map<String, Any>
}
