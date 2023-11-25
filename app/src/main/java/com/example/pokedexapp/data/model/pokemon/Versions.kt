package com.example.pokedexapp.data.model.pokemon


import com.google.gson.annotations.SerializedName

data class Versions(
    @SerializedName("generation-v")
    val generationV: com.example.pokedexapp.data.model.pokemon.GenerationV,
    @SerializedName("generation-vi")
    val generationVi: com.example.pokedexapp.data.model.pokemon.GenerationVi,
    @SerializedName("generation-vii")
    val generationVii: com.example.pokedexapp.data.model.pokemon.GenerationVii,
    @SerializedName("generation-viii")
    val generationViii: com.example.pokedexapp.data.model.pokemon.GenerationViii,
    @SerializedName("generation-i")
    val generationİ: com.example.pokedexapp.data.model.pokemon.Generationİ,
    @SerializedName("generation-ii")
    val generationİi: com.example.pokedexapp.data.model.pokemon.Generationİi,
    @SerializedName("generation-iii")
    val generationİii: com.example.pokedexapp.data.model.pokemon.Generationİii,
    @SerializedName("generation-iv")
    val generationİv: com.example.pokedexapp.data.model.pokemon.Generationİv
)