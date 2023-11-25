package com.example.pokedexapp.data.model.pokemon


import com.google.gson.annotations.SerializedName

data class Ability(
    @SerializedName("ability")
    val ability: com.example.pokedexapp.data.model.pokemon.AbilityX,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    @SerializedName("slot")
    val slot: Int
)