package com.example.pokedexapp.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokedexapp.MainDispatcherRule
import com.example.pokedexapp.data.repository.FakePokemonRepositoryImpl
import com.example.pokedexapp.domain.model.PokemonAbout
import com.example.pokedexapp.domain.model.PokemonDetail
import com.example.pokedexapp.domain.model.PokemonType
import com.example.pokedexapp.domain.use_case.GetPokemonAboutByIDUseCase
import com.example.pokedexapp.domain.use_case.GetPokemonByIDUseCase
import com.example.pokedexapp.getOrAwaitValueTest
import com.example.pokedexapp.presentation.view.detail.DetailViewModel
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setup() {
        detailViewModel = DetailViewModel(
            getPokemonAboutByIDUseCase = GetPokemonAboutByIDUseCase(FakePokemonRepositoryImpl()),
            getPokemonByIDUseCase = GetPokemonByIDUseCase(FakePokemonRepositoryImpl())
        )
        detailViewModel.loadData("1")
    }

    @Test
    fun `check detail`() = runBlocking {
        val value = detailViewModel.pokemonDetail.getOrAwaitValueTest()
        Truth.assertThat(value).isEqualTo(
            PokemonDetail(
                id = "#001",
                name = "Bulbasaur",
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                types = listOf(
                    PokemonType(
                        typeName = "Grass",
                        typeColor = 2131034218
                    ), PokemonType(
                        typeName = "Poison",
                        typeColor = 2131034880
                    )
                ),
                weight = "6,9 kg",
                height = "0,7 m",
                moves = listOf(
                    "razor-wind",
                ),
                hp = "045",
                hpInt = 45,
                attack = "049",
                attackInt = 49,
                defense = "049",
                defenseInt = 49,
                specialAttack = "065",
                specialAttackInt = 65,
                specialDefence = "065",
                specialDefenceInt = 65,
                speed = "045",
                speedInt = 45,
                color = 2131034218,
                colorTransparent = 2131034218
            )
        )
    }

    @Test
    fun `check about`() = runBlocking {
        val value = detailViewModel.pokemonAbout.getOrAwaitValueTest()
        Truth.assertThat(value).isEqualTo(
            PokemonAbout("This is pokemon detail")
        )
    }
}