package com.berkerdgn.pokemon_app.for_room.converters

import androidx.room.TypeConverter
import com.berkerdgn.pokemon_app.model.room_model.ComparisonAbility
import com.berkerdgn.pokemon_app.model.room_model.ComparisonAbilityX
import com.berkerdgn.pokemon_app.model.room_model.ComparisonStat
import com.berkerdgn.pokemon_app.model.room_model.ComparisonStatX
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {


    @TypeConverter
    fun fromStringRoComparisonStateList(value:String):List<ComparisonStat>{
        val listType = object : TypeToken<List<ComparisonStat>>(){}.type
        return Gson().fromJson(value,listType)
    }

    @TypeConverter
    fun fromComparisonStatListToString(list: List<ComparisonStat>):String{
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromComparisonStatX(value: ComparisonStatX):String{
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toComparisonStatX(value: String):ComparisonStatX{
        return Gson().fromJson(value,ComparisonStatX::class.java)
    }



    @TypeConverter
    fun fromStringToComparisonAbilityList(value: String): List<ComparisonAbility>{
        val listType = object : TypeToken<List<ComparisonAbility>>(){}.type
        return  Gson().fromJson(value,listType)
    }

    @TypeConverter
    fun fromComparisonAbilityListToString(list: List<ComparisonAbility>): String{
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromComparisonAbilityX(value: ComparisonAbilityX): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toComparisonAbilityX(value: String): ComparisonAbilityX {
        return Gson().fromJson(value, ComparisonAbilityX::class.java)
    }
}