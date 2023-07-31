package com.berkerdgn.pokemon_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkerdgn.pokemon_app.model.for_detail_model.PokemonDetailModel
import com.berkerdgn.pokemon_app.repo.PokemonRepository
import com.berkerdgn.pokemon_app.repo.PokemonRepositoryImpl
import com.berkerdgn.pokemon_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {
    private var _pokemonDetail = MutableLiveData<Resource<PokemonDetailModel>>()

    val pokemonDetails: LiveData<Resource<PokemonDetailModel>>
        get() = _pokemonDetail

    fun   accessibleGetPokemonDetail(pokemonName: String){
        getPokemonDetail(pokemonName)
    }

    private fun getPokemonDetail(pokemonName: String){
        _pokemonDetail.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.getPokemonDetail(pokemonName)
            _pokemonDetail.value = response
        }

   }

}