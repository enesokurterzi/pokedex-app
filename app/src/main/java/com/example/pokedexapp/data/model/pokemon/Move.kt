package com.example.pokedexapp.data.model.pokemon


import com.google.gson.annotations.SerializedName

data class Move(
    @SerializedName("move")
    val move: com.example.pokedexapp.data.model.pokemon.MoveX,
    @SerializedName("version_group_details")
    val versionGroupDetails: List<com.example.pokedexapp.data.model.pokemon.VersionGroupDetail>
)