package com.example.pokedexapp.data.model.pokemon


import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("dream_world")
    val dreamWorld: com.example.pokedexapp.data.model.pokemon.DreamWorld,
    @SerializedName("home")
    val home: com.example.pokedexapp.data.model.pokemon.Home,
    @SerializedName("official-artwork")
    val officialArtwork: com.example.pokedexapp.data.model.pokemon.OfficialArtwork
)