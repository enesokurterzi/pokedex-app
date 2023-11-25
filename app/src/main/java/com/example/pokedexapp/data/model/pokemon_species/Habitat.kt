package com.example.pokedexapp.data.model.pokemon_species


import com.google.gson.annotations.SerializedName

data class Habitat(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)