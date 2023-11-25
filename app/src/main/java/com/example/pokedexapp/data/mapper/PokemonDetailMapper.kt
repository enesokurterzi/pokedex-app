package com.example.pokedexapp.data.mapper

import androidx.core.graphics.toColor
import com.example.pokedexapp.data.model.pokemon.PokemonDto
import com.example.pokedexapp.data.model.pokemon.Stat
import com.example.pokedexapp.domain.model.PokemonColor
import com.example.pokedexapp.domain.model.PokemonDetail
import com.example.pokedexapp.domain.model.PokemonType

fun PokemonDto.toPokemonDetail(): PokemonDetail {
    return PokemonDetail(
        id = idOrganize(id.toString()),
        name = nameOrganize(name),
        imageUrl = sprites.other.officialArtwork.frontDefault,
        types = types.map { it.type.name }.map { pokemonTypeCreate(it) },
        weight = weightAndHeightOrganize(weight) + " kg",
        height = weightAndHeightOrganize(height) + " m",
        moves = moves.map { it.move.name },
        hp = statOrganizer(statFinder(stats, "hp")),
        hpInt = statFinder(stats, "hp").toInt(),
        attack = statOrganizer(statFinder(stats, "attack")),
        attackInt = statFinder(stats, "attack").toInt(),
        defense = statOrganizer(statFinder(stats, "defense")),
        defenseInt = statFinder(stats, "defense").toInt(),
        specialAttack = statOrganizer(statFinder(stats, "special-attack")),
        specialAttackInt = statFinder(stats, "special-attack").toInt(),
        specialDefence = statOrganizer(statFinder(stats, "special-defense")),
        specialDefenceInt = statFinder(stats, "special-defense").toInt(),
        speed = statOrganizer(statFinder(stats, "speed")),
        speedInt = statFinder(stats, "speed").toInt(),
        color = PokemonColor.getColor(types.map { it.type.name }[0]).color,
        colorTransparent = PokemonColor.getColor(types.map { it.type.name }[0]).color,
        )

}

fun statFinder(stats: List<Stat>, statName: String): String {
    return stats.find { it.stat.name == statName }?.baseStat.toString()
}

fun statOrganizer(stat: String): String {
    return stat.padStart(3, '0')
}

fun weightAndHeightOrganize(number: Int): String {
    val formattedNumber = if (number < 10) {
        "0$number"
    } else {
        number.toString()
    }

    return if (formattedNumber.length == 2) {
        formattedNumber[0] + "," + formattedNumber[1]
    } else {
        formattedNumber.substring(
            0,
            formattedNumber.length - 1
        ) + "," + formattedNumber[formattedNumber.length - 1]
    }
}

fun pokemonTypeCreate(typeName: String): PokemonType {
    return PokemonType(
        typeName = nameOrganize(typeName),
        typeColor = PokemonColor.getColor(typeName).color
    )
}

fun nameOrganize(name: String): String {
    return name.replaceFirstChar { it.uppercase() }
}

fun idOrganize(id: String): String {
    return "#" + id.padStart(3, '0')
}
