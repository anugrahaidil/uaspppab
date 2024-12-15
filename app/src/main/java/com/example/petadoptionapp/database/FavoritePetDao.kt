package com.example.petadoptionapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoritePetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favoritePet: FavoritePetEntity): Long

    @Query("SELECT id FROM favorite_pets")
    suspend fun getFavoritePetIds(): List<String>

    @Delete
    suspend fun removeFavorite(favoritePet: FavoritePetEntity): Int
}
