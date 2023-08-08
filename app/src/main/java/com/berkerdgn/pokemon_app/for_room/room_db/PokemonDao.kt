package com.berkerdgn.pokemon_app.for_room.room_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.berkerdgn.pokemon_app.model.for_detail_model.PokemonDetailModel
import com.berkerdgn.pokemon_app.model.room_model.ComparisonPokemonModel

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(savedPokemon:ComparisonPokemonModel)

    @Delete
    suspend fun deletePokemon(savedPokemon: ComparisonPokemonModel)

    @Query("SELECT * FROM savedPokemons")
    fun savedPokemonLiveData(): List<ComparisonPokemonModel>




}