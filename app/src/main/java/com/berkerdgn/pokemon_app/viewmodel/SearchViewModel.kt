package com.berkerdgn.pokemon_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkerdgn.pokemon_app.model.for_detail_model.PokemonDetailModel
import com.berkerdgn.pokemon_app.repo.PokemonRepository
import com.berkerdgn.pokemon_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {
    private var _searchPokemon = MutableLiveData<Resource<PokemonDetailModel>>()

    val searchPokemon: LiveData<Resource<PokemonDetailModel>>
        get() = _searchPokemon

    fun   accessibleGetSearchPokemon(pokemonName: String){
        getSearchPokemon(pokemonName)
    }

    private fun getSearchPokemon(pokemonName: String){
        _searchPokemon.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.getPokemonDetail(pokemonName)
            _searchPokemon.value = response
        }

    }

}