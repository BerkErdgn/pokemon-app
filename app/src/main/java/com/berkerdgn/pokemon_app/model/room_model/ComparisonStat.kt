package com.berkerdgn.pokemon_app.model.room_model

import com.berkerdgn.pokemon_app.model.for_detail_model.StatX


data class ComparisonStat(
    val base_stat: Int,
    val effort: Int,
    val stat: ComparisonStatX
)
