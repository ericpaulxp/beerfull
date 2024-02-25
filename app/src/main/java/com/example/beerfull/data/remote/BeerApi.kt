package com.example.beerfull.data.remote

import com.example.beerfull.domain.model.Beer
import com.example.beerfull.utils.Constants.ALL_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApi {

    @GET(ALL_ENDPOINT)
    suspend fun getAllBeers(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): List<Beer>

    @GET(ALL_ENDPOINT)
    suspend fun searchBeers(
        @Query("beer_name") searchQuery: String,
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): List<Beer>
}