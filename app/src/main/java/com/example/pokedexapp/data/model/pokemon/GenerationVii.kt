package com.example.pokedexapp.data.model.pokemon


import com.google.gson.annotations.SerializedName

data class GenerationVii(
    @SerializedName("icons")
    val icons: com.example.pokedexapp.data.model.pokemon.İcons,
    @SerializedName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: com.example.pokedexapp.data.model.pokemon.UltraSunUltraMoon
)