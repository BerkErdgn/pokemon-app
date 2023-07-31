package com.berkerdgn.pokemon_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkerdgn.pokemon_app.model.for_all_model.AllPokemonModel
import com.berkerdgn.pokemon_app.repo.PokemonRepository
import com.berkerdgn.pokemon_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PokemonRepository,

) :ViewModel() {
    private var pokemons = MutableLiveData<Resource<AllPokemonModel>>()
    val pokemonList: LiveData<Resource<AllPokemonModel>>
        get() = pokemons

init {
    getPokemons()
}

    private fun getPokemons(){
        pokemons.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.getAllPokemons()
            pokemons.value = response
        }
    }

}