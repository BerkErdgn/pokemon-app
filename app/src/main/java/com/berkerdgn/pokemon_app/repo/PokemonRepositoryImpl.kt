package com.berkerdgn.pokemon_app.repo

import com.berkerdgn.pokemon_app.api.PokemonApi
import com.berkerdgn.pokemon_app.for_room.room_db.PokemonDao
import com.berkerdgn.pokemon_app.model.for_all_model.AllPokemonModel
import com.berkerdgn.pokemon_app.model.for_detail_model.PokemonDetailModel
import com.berkerdgn.pokemon_app.model.room_model.ComparisonPokemonModel
import com.berkerdgn.pokemon_app.util.Resource
import java.lang.Exception
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi : PokemonApi,
    private val pokemonDao: PokemonDao
): PokemonRepository {
    override suspend fun getAllPokemons(): Resource<AllPokemonModel> {
        return try {
            val response = pokemonApi.getAllPokemons()
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                }?: Resource.error("No data could be retrieved!!", data = null)
            }else{
                Resource.error("No data could be retrieved!!",data = null)
            }
        }catch (e: Exception){
            Resource.error("No data could be retrieved!!", data = null)
        }
    }

    override suspend fun getPokemonDetail(pokemonName: String): Resource<PokemonDetailModel> {
        return try {
            val response = pokemonApi.getPokemonDetail(pokemonName)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                }?:Resource.error("No detail data could be retrieved!!", data = null)
            }else{
                Resource.error("No detail data could be retrieved!!", data = null)
            }
        }catch (e: Exception){
            Resource.error("No detail data could be retrieved!!", data = null)
        }
    }


    //for Room
    override suspend fun insertSavedPokemon(savedPokemon: ComparisonPokemonModel) {
        pokemonDao.insertPokemon(savedPokemon)
    }

    override suspend fun deleteSavedPokemon(savedPokemon: ComparisonPokemonModel) {
        pokemonDao.deletePokemon(savedPokemon)
    }

    override fun getSavedPokemon(): List<ComparisonPokemonModel> {
        return pokemonDao.savedPokemonLiveData()
    }


}