package com.example.petadoptionapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PetDao {
    @Insert
    suspend fun insertFavorite(pet: FavoritePetEntity)

    @Query("SELECT * FROM favorite_pets")
    suspend fun getFavorites(): List<FavoritePetEntity>
}