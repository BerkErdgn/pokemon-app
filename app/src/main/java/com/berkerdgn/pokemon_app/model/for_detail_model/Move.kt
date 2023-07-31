package com.berkerdgn.pokemon_app.model.for_detail_model

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)