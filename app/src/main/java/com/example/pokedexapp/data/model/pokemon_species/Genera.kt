package com.example.pokedexapp.data.model.pokemon_species


import com.google.gson.annotations.SerializedName

data class Genera(
    @SerializedName("genus")
    val genus: String,
    @SerializedName("language")
    val language: Language
)