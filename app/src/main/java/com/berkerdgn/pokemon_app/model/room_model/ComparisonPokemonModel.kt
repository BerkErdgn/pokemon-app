package com.berkerdgn.pokemon_app.model.room_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.berkerdgn.pokemon_app.model.for_detail_model.Ability
import com.berkerdgn.pokemon_app.model.for_detail_model.Stat


@Entity(tableName = "savedPokemons")
data class ComparisonPokemonModel (

    @ColumnInfo(name = "abilities")
    val abilities: List<ComparisonAbility>,
    @ColumnInfo(name = "height")
    val height: Int,
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "weight")
    val weight: Int,
    @ColumnInfo(name = "stats")
    val stats: List<ComparisonStat>,

    @PrimaryKey(autoGenerate = true)
    val roomId : Int? = null

)