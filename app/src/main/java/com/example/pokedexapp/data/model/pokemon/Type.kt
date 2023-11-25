package com.example.pokedexapp.data.model.pokemon


import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: com.example.pokedexapp.data.model.pokemon.TypeX
)