package com.berkerdgn.pokemon_app.api

import com.berkerdgn.pokemon_app.model.AllPokemonModel
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApi {
    //https://pokeapi.co/api/v2/pokemon

    @GET("pokemon")
    suspend fun getAllPokemons(
    ): Response<AllPokemonModel>

}