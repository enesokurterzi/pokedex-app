package com.example.pokedexapp.data.model.pokemon


import com.google.gson.annotations.SerializedName

data class Generationİii(
    @SerializedName("emerald")
    val emerald: com.example.pokedexapp.data.model.pokemon.Emerald,
    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: com.example.pokedexapp.data.model.pokemon.FireredLeafgreen,
    @SerializedName("ruby-sapphire")
    val rubySapphire: com.example.pokedexapp.data.model.pokemon.RubySapphire
)