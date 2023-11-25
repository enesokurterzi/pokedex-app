package com.example.pokedexapp.data.model.pokemon


import com.google.gson.annotations.SerializedName

data class VersionGroup(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)