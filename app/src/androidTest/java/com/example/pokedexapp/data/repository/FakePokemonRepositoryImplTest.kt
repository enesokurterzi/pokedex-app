package com.example.pokedexapp.data.repository

import com.example.pokedexapp.data.model.pokemon.Move
import com.example.pokedexapp.data.model.pokemon.MoveX
import com.example.pokedexapp.data.model.pokemon.OfficialArtwork
import com.example.pokedexapp.data.model.pokemon.Other
import com.example.pokedexapp.data.model.pokemon.PokemonDto
import com.example.pokedexapp.data.model.pokemon.Species
import com.example.pokedexapp.data.model.pokemon.Sprites
import com.example.pokedexapp.data.model.pokemon.Stat
import com.example.pokedexapp.data.model.pokemon.StatX
import com.example.pokedexapp.data.model.pokemon.Type
import com.example.pokedexapp.data.model.pokemon.TypeX
import com.example.pokedexapp.data.model.pokemon_list.PokemonListDto
import com.example.pokedexapp.data.model.pokemon_list.Result
import com.example.pokedexapp.data.model.pokemon_species.FlavorTextEntry
import com.example.pokedexapp.data.model.pokemon_species.Language
import com.example.pokedexapp.data.model.pokemon_species.PokemonSpeciesDto
import com.example.pokedexapp.data.model.pokemon_species.Version
import com.example.pokedexapp.domain.repository.PokemonRepository

class FakePokemonRepositoryImplTest : PokemonRepository {
    override suspend fun getAllPokemons(): PokemonListDto {
        return PokemonListDto(
            count = 1302,
            next = "null",
            previous = "null",
            results = listOf(
                Result(
                    name = "bulbasaur",
                    url = "https://pokeapi.co/api/v2/pokemon/1/"
                ), Result(
                    name = "ivysaur",
                    url = "https://pokeapi.co/api/v2/pokemon/2/"
                ), Result(
                    name = "venusaur",
                    url = "https://pokeapi.co/api/v2/pokemon/3/"
                ), Result(
                    name = "charmander",
                    url = "https://pokeapi.co/api/v2/pokemon/4/"
                )
            )
        )
    }

    override suspend fun getPokemonByID(pokemonID: String): PokemonDto {
        return PokemonDto(
            id = 1,
            name = "Bulbasaur",
            types = listOf(
                Type(
                    slot = 1,
                    type = TypeX(
                        name = "grass",
                        url = "https://pokeapi.co/api/v2/type/12/"
                    )
                ),
                Type(
                    slot = 2,
                    type = TypeX(
                        name = "poison",
                        url = "https://pokeapi.co/api/v2/type/4/"
                    )
                )
            ),
            weight = 69,
            height = 7,
            moves = listOf(
                Move(
                    MoveX(
                        name = "razor-wind",
                        url = ""
                    ),
                    versionGroupDetails = listOf()
                )
            ),
            stats = listOf(
                Stat(
                    baseStat = 45,
                    effort = 0,
                    stat = StatX(
                        name = "hp",
                        url = ""
                    )
                ),
                Stat(
                    baseStat = 49,
                    effort = 0,
                    stat = StatX(
                        name = "attack",
                        url = ""
                    )
                ),
                Stat(
                    baseStat = 49,
                    effort = 0,
                    stat = StatX(
                        name = "defense",
                        url = ""
                    )
                ),
                Stat(
                    baseStat = 65,
                    effort = 0,
                    stat = StatX(
                        name = "special-attack",
                        url = ""
                    )
                ),
                Stat(
                    baseStat = 65,
                    effort = 0,
                    stat = StatX(
                        name = "special-defense",
                        url = ""
                    )
                ),
                Stat(
                    baseStat = 45,
                    effort = 0,
                    stat = StatX(
                        name = "speed",
                        url = ""
                    )
                )
            ),
            abilities = listOf(),
            baseExperience = 0,
            forms = listOf(),
            gameİndices = listOf(),
            heldİtems = listOf(),
            isDefault = true,
            locationAreaEncounters = "",
            order = 0,
            pastAbilities = listOf(),
            pastTypes = listOf(),
            species = Species(
                name = "",
                url = ""
            ),
            sprites = Sprites(
                backShinyFemale = null,
                backShiny = null,
                backFemale = null,
                backDefault = null,
                frontDefault = null,
                frontShinyFemale = null,
                frontShiny = null,
                frontFemale = null,
                versions = null,
                other = Other(
                    dreamWorld = null,
                    home = null,
                    officialArtwork = OfficialArtwork(
                        frontShiny = null,
                        frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                    )
                )
            )
        )
    }

    override suspend fun getPokemonAboutByID(pokemonID: String): PokemonSpeciesDto {
        return PokemonSpeciesDto(
            baseHappiness = null,
            captureRate = null,
            color = null,
            eggGroups = null,
            evolutionChain = null,
            evolvesFromSpecies = null,
            flavorTextEntries = listOf(
                FlavorTextEntry(
                    flavorText = "This is pokemon detail",
                    language = Language("", ""),
                    version = Version("", "")
                )
            ),
            formDescriptions = null,
            formsSwitchable = null,
            genderRate = null,
            genera = null,
            generation = null,
            growthRate = null,
            habitat = null,
            hasGenderDifferences = null,
            hatchCounter = null,
            id = null,
            isBaby = null,
            isLegendary = null,
            isMythical = null,
            name = null,
            names = null,
            order = null,
            palParkEncounters = null,
            pokedexNumbers = null,
            shape = null,
            varieties = null
        )
    }
}