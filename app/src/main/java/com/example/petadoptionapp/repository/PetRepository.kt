package com.example.petadoptionapp.repository

import com.example.petadoptionapp.database.FavoritePetDao
import com.example.petadoptionapp.database.FavoritePetEntity
import com.example.petadoptionapp.model.Pet
import com.example.petadoptionapp.network.PetApiService

class PetRepository(
    private val api: PetApiService,
    private val favoritePetDao: FavoritePetDao
) {
    suspend fun getPets(): List<Pet> {
        val petsFromApi = api.getPets()
        val favoriteIds = favoritePetDao.getFavoritePetIds()
        return petsFromApi.map { pet ->
            pet.copy(isFavorite = favoriteIds.contains(pet.id))
        }
    }

    suspend fun getPetsByCategory(category: String): List<Pet> {
        val allPets = getPets()
        return allPets.filter { it.category.equals(category, ignoreCase = true) }
    }

    suspend fun toggleFavorite(pet: Pet) {
        if (pet.isFavorite) {
            favoritePetDao.removeFavorite(FavoritePetEntity(pet.id))
        } else {
            favoritePetDao.addFavorite(
                FavoritePetEntity(
                    id = pet.id,
                    name = pet.name,
                    breed = pet.breed,
                    age = pet.age,
                    image = pet.imageUrl
                )
            )
        }
    }

    // Fungsi pembantu untuk konversi dari Pet ke FavoritePetEntity
    private fun petToEntity(pet: Pet): FavoritePetEntity {
        return FavoritePetEntity(
            id = pet.id,
            name = pet.name,
            breed = pet.breed,
            age = pet.age,
            image = pet.imageUrl
        )
    }

    // Fungsi pembantu untuk konversi dari FavoritePetEntity ke Pet
    private fun entityToPet(entity: FavoritePetEntity): Pet {
        return Pet(
            id = entity.id,
            name = entity.name,
            breed = entity.breed,
            age = entity.age,
            imageUrl = entity.image,
            isFavorite = true
        )
    }

}

