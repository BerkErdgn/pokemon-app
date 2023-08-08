package com.berkerdgn.pokemon_app.repo

import com.berkerdgn.pokemon_app.model.for_all_model.AllPokemonModel
import com.berkerdgn.pokemon_app.model.for_detail_model.PokemonDetailModel
import com.berkerdgn.pokemon_app.model.room_model.ComparisonPokemonModel
import com.berkerdgn.pokemon_app.util.Resource

interface PokemonRepository {

    suspend fun getAllPokemons (): Resource<AllPokemonModel>

    suspend fun getPokemonDetail(pokemonName: String): Resource<PokemonDetailModel>

    //for Dao
    suspend fun insertSavedPokemon(savedPokemon: ComparisonPokemonModel)

    suspend fun deleteSavedPokemon(savedPokemon: ComparisonPokemonModel)

    fun getSavedPokemon(): List<ComparisonPokemonModel>

}