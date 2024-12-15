package com.example.petadoptionapp.model

data class Pet(
    val id: String,
    val name: String,
    val breed: String,
    val age: Int,
    val distance: Double,
    val imageUrl: String,
    val category: String, // Tambahkan kategori: dogs, cats, parrot, rabbit
    var isFavorite: Boolean = false
)

