package com.example.pokedexapp.data.model.pokemon


import com.google.gson.annotations.SerializedName

data class Generationİi(
    @SerializedName("crystal")
    val crystal: com.example.pokedexapp.data.model.pokemon.Crystal,
    @SerializedName("gold")
    val gold: com.example.pokedexapp.data.model.pokemon.Gold,
    @SerializedName("silver")
    val silver: com.example.pokedexapp.data.model.pokemon.Silver
)