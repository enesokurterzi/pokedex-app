package com.example.pokedexapp.domain.model

import androidx.annotation.ColorRes
import com.example.pokedexapp.R

sealed class PokemonColor(
    @ColorRes val color: Int
) {
    data object BugColor : PokemonColor(
        color = R.color.bugColor
    )

    data object DarkColor : PokemonColor(
        color = R.color.darkColor
    )

    data object DragonColor : PokemonColor(
        color = R.color.dragonColor
    )

    data object ElectricColor : PokemonColor(
        color = R.color.electricColor
    )

    data object FairyColor : PokemonColor(
        color = R.color.fairyColor
    )

    data object FightingColor : PokemonColor(
        color = R.color.fightingColor
    )

    data object FireColor : PokemonColor(
        color = R.color.fireColor
    )

    data object FlyingColor : PokemonColor(
        color = R.color.flyingColor
    )

    data object GhostColor : PokemonColor(
        color = R.color.ghostColor
    )

    data object NormalColor : PokemonColor(
        color = R.color.normalColor
    )

    data object GrassColor : PokemonColor(
        color = R.color.grassColor
    )

    data object GroundColor : PokemonColor(
        color = R.color.groundColor
    )

    data object IceColor : PokemonColor(
        color = R.color.iceColor
    )

    data object PoisonColor : PokemonColor(
        color = R.color.poisonColor
    )

    data object PsychicColor : PokemonColor(
        color = R.color.psychicColor
    )

    data object RockColor : PokemonColor(
        color = R.color.rockColor
    )

    data object SteelColor : PokemonColor(
        color = R.color.steelColor
    )

    data object WaterColor : PokemonColor(
        color = R.color.waterColor
    )

    companion object {
        fun getColor(type: String): PokemonColor {
            return when (type) {
                "bug" -> BugColor
                "dark" -> DarkColor
                "dragon" -> DragonColor
                "electric" -> ElectricColor
                "fairy" -> FairyColor
                "fighting" -> FightingColor
                "fire" -> FireColor
                "flying" -> FlyingColor
                "ghost" -> GhostColor
                "normal" -> NormalColor
                "grass" -> GrassColor
                "ground" -> GroundColor
                "ice" -> IceColor
                "poison" -> PoisonColor
                "psychic" -> PsychicColor
                "rock" -> RockColor
                "steel" -> SteelColor
                "water" -> WaterColor
                else -> NormalColor
            }
        }
    }

}