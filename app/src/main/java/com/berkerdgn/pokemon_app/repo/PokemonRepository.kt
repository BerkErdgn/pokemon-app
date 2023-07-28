package com.berkerdgn.pokemon_app.repo

import com.berkerdgn.pokemon_app.model.AllPokemonModel
import com.berkerdgn.pokemon_app.util.Resource
import retrofit2.Response

interface PokemonRepository {

    suspend fun getAllPokemons (): Resource<AllPokemonModel>

}