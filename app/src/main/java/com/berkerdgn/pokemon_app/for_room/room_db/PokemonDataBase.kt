package com.berkerdgn.pokemon_app.for_room.room_db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.berkerdgn.pokemon_app.for_room.converters.ComparisonAbilityXConverter
import com.berkerdgn.pokemon_app.for_room.converters.ComparisonStatXConverter
import com.berkerdgn.pokemon_app.for_room.converters.Converters
import com.berkerdgn.pokemon_app.model.room_model.ComparisonPokemonModel

@Database(entities = [ComparisonPokemonModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class PokemonDataBase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

}