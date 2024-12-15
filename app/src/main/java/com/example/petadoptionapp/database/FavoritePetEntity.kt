package com.example.petadoptionapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_pets")
data class FavoritePetEntity(
    @PrimaryKey val id: String,
    val name: String = "",
    val breed: String = "",
    val age: Int = 0,
    val image: String = ""
)

