package com.berkerdgn.pokemon_app.for_room.converters

import androidx.room.TypeConverter
import com.berkerdgn.pokemon_app.model.room_model.ComparisonStatX
import com.google.gson.Gson

class ComparisonStatXConverter {

    @TypeConverter
    fun fromString(value: String): ComparisonStatX{
        return Gson().fromJson(value,ComparisonStatX::class.java)
    }

    @TypeConverter
    fun fromComparisonStatX(comparisonStatX: ComparisonStatX): String{
        return Gson().toJson(comparisonStatX)
    }


}