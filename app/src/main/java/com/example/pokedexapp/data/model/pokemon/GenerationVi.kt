package com.example.pokedexapp.data.model.pokemon


import com.google.gson.annotations.SerializedName

data class GenerationVi(
    @SerializedName("omegaruby-alphasapphire")
    val omegarubyAlphasapphire: com.example.pokedexapp.data.model.pokemon.OmegarubyAlphasapphire,
    @SerializedName("x-y")
    val xY: com.example.pokedexapp.data.model.pokemon.XY
)