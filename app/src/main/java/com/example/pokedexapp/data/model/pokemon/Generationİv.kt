package com.example.pokedexapp.data.model.pokemon


import com.google.gson.annotations.SerializedName

data class Generationİv(
    @SerializedName("diamond-pearl")
    val diamondPearl: com.example.pokedexapp.data.model.pokemon.DiamondPearl,
    @SerializedName("heartgold-soulsilver")
    val heartgoldSoulsilver: com.example.pokedexapp.data.model.pokemon.HeartgoldSoulsilver,
    @SerializedName("platinum")
    val platinum: com.example.pokedexapp.data.model.pokemon.Platinum
)