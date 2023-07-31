package com.berkerdgn.pokemon_app.repo

import com.berkerdgn.pokemon_app.model.for_all_model.AllPokemonModel
import com.berkerdgn.pokemon_app.model.for_detail_model.PokemonDetailModel
import com.berkerdgn.pokemon_app.util.Resource

interface PokemonRepository {

    suspend fun getAllPokemons (): Resource<AllPokemonModel>

    suspend fun getPokemonDetail(pokemonName: String): Resource<PokemonDetailModel>

}