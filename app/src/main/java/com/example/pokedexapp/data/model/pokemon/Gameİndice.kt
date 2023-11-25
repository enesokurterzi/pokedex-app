package com.example.pokedexapp.data.model.pokemon


import com.google.gson.annotations.SerializedName

data class Gameİndice(
    @SerializedName("game_index")
    val gameİndex: Int,
    @SerializedName("version")
    val version: com.example.pokedexapp.data.model.pokemon.Version
)