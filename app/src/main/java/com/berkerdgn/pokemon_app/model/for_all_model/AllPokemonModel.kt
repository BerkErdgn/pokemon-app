package com.berkerdgn.pokemon_app.model.for_all_model

data class AllPokemonModel(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonResult>
)