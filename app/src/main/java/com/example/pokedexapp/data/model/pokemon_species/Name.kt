package com.example.pokedexapp.data.model.pokemon_species


import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("language")
    val language: Language,
    @SerializedName("name")
    val name: String
)