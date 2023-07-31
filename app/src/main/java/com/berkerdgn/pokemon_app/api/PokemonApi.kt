package com.berkerdgn.pokemon_app.api

import com.berkerdgn.pokemon_app.model.for_all_model.AllPokemonModel
import com.berkerdgn.pokemon_app.model.for_detail_model.PokemonDetailModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    //https://pokeapi.co/api/v2/pokemon
    //https://pokeapi.co/api/v2/pokemon/ditto
    @GET("pokemon")
    suspend fun getAllPokemons(
    ): Response<AllPokemonModel>

    @GET("pokemon/{pokemonName}")
    suspend fun getPokemonDetail(
    @Path("pokemonName")pokemonName: String
    ):Response<PokemonDetailModel>
}