package com.example.pokedexapp.domain.model

import android.graphics.Color

data class PokemonDetail(
    val id: String,
    val name: String,
    val imageUrl: String,
    val types: List<PokemonType>,
    val weight: String,
    val height: String,
    val moves: List<String>,
    val hp: String,
    val hpInt: Int,
    val attack: String,
    val attackInt: Int,
    val defense: String,
    val defenseInt: Int,
    val specialAttack: String,
    val specialAttackInt: Int,
    val specialDefence: String,
    val specialDefenceInt: Int,
    val speed: String,
    val speedInt: Int,
    val color: Int,
    val colorTransparent: Int
)
