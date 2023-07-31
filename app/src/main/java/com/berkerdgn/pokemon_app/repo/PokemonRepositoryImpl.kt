package com.berkerdgn.pokemon_app.repo

import com.berkerdgn.pokemon_app.api.PokemonApi
import com.berkerdgn.pokemon_app.model.for_all_model.AllPokemonModel
import com.berkerdgn.pokemon_app.model.for_detail_model.PokemonDetailModel
import com.berkerdgn.pokemon_app.util.Resource
import java.lang.Exception
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi : PokemonApi
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


}