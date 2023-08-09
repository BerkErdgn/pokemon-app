package com.berkerdgn.pokemon_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkerdgn.pokemon_app.model.room_model.ComparisonAbility
import com.berkerdgn.pokemon_app.model.room_model.ComparisonPokemonModel
import com.berkerdgn.pokemon_app.model.room_model.ComparisonStat
import com.berkerdgn.pokemon_app.repo.PokemonRepository
import com.berkerdgn.pokemon_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private var _savedPokemon = MutableLiveData<List<ComparisonPokemonModel>>()

    val savedPokemon : LiveData<List<ComparisonPokemonModel>>
        get() = _savedPokemon


    fun getSavedPokemon(){
        viewModelScope.launch {
            val response = repository.getSavedPokemon()
            _savedPokemon.value = response
        }
    }

    fun deleteSavedPokemon(savedPokemon: ComparisonPokemonModel){
        viewModelScope.launch {
            repository.deleteSavedPokemon(savedPokemon = savedPokemon)
        }
    }


    private fun insertSavedPokemon(savedPokemon: ComparisonPokemonModel){
        viewModelScope.launch {
            repository.insertSavedPokemon(savedPokemon = savedPokemon)
        }
    }


    fun savePokemon(
        abilities: List<ComparisonAbility>,
        height: Int,
        id: Int,
        name: String,
        weight: Int,
        stats: List<ComparisonStat>,
    ){
        val savedStation = ComparisonPokemonModel(abilities,height,id,name, weight, stats)
        insertSavedPokemon(savedStation)
    }





}