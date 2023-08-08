package com.berkerdgn.pokemon_app.for_room.converters

import androidx.room.TypeConverter
import com.berkerdgn.pokemon_app.model.room_model.ComparisonAbilityX
import com.google.gson.Gson

class ComparisonAbilityXConverter {

    @TypeConverter
    fun fromString(value:String): ComparisonAbilityX{
        return Gson().fromJson(value, ComparisonAbilityX::class.java)
    }

    @TypeConverter
    fun fromComparisonAbilityX(abilityX: ComparisonAbilityX): String{
        return Gson().toJson(abilityX)
    }



}