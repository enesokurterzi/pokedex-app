package com.example.pokedexapp.data.model.pokemon


import com.google.gson.annotations.SerializedName

data class PokemonDto(
    @SerializedName("abilities")
    val abilities: List<com.example.pokedexapp.data.model.pokemon.Ability>,
    @SerializedName("base_experience")
    val baseExperience: Int,
    @SerializedName("forms")
    val forms: List<com.example.pokedexapp.data.model.pokemon.Form>,
    @SerializedName("game_indices")
    val gameİndices: List<com.example.pokedexapp.data.model.pokemon.Gameİndice>,
    @SerializedName("height")
    val height: Int,
    @SerializedName("held_items")
    val heldİtems: List<Any>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String,
    @SerializedName("moves")
    val moves: List<com.example.pokedexapp.data.model.pokemon.Move>,
    @SerializedName("name")
    val name: String,
    @SerializedName("order")
    val order: Int,
    @SerializedName("past_abilities")
    val pastAbilities: List<Any>,
    @SerializedName("past_types")
    val pastTypes: List<Any>,
    @SerializedName("species")
    val species: com.example.pokedexapp.data.model.pokemon.Species,
    @SerializedName("sprites")
    val sprites: com.example.pokedexapp.data.model.pokemon.Sprites,
    @SerializedName("stats")
    val stats: List<com.example.pokedexapp.data.model.pokemon.Stat>,
    @SerializedName("types")
    val types: List<com.example.pokedexapp.data.model.pokemon.Type>,
    @SerializedName("weight")
    val weight: Int
)