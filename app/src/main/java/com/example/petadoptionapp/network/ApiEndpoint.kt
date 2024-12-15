package com.example.petadoptionapp.network

import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {
    @GET("{db}/{table}")
    fun getAllData(
        @Path("db") db: String,
        @Path("table") table: String,
        @Query("options") options: String? = null,
        @Query("projection") projection: String? = null
    ): Call<List<Map<String, Any>>>

    @GET("{db}/{table}/{id}")
    fun getDetail(
        @Path("db") db: String,
        @Path("table") table: String,
        @Path("id") id: String
    ): Call<Map<String, Any>>

    @POST("{db}/{table}")
    fun createData(
        @Path("db") db: String,
        @Path("table") table: String,
        @Body body: Map<String, Any>
    ): Call<Map<String, Any>>

    @PUT("{db}/{table}/{id}")
    fun updateData(
        @Path("db") db: String,
        @Path("table") table: String,
        @Path("id") id: String,
        @Body body: Map<String, Any>
    ): Call<Map<String, Any>>

    @DELETE("{db}/{table}/{id}")
    fun deleteData(
        @Path("db") db: String,
        @Path("table") table: String,
        @Path("id") id: String
    ): Call<Map<String, Any>>
}