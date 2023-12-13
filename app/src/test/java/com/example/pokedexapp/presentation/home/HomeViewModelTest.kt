package com.example.pokedexapp.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokedexapp.MainDispatcherRule
import com.example.pokedexapp.data.repository.FakePokemonRepositoryImpl
import com.example.pokedexapp.domain.model.Pokemon
import com.example.pokedexapp.getOrAwaitValueTest
import com.example.pokedexapp.presentation.view.home.HomeViewModel
import com.example.pokedexapp.presentation.view.home.SortEvent
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        homeViewModel = HomeViewModel(FakePokemonRepositoryImpl())
        homeViewModel.shownList.getOrAwaitValueTest()
    }

    @Test
    fun `check list`() = runBlocking {
        val value = homeViewModel.shownList.getOrAwaitValueTest()
        Truth.assertThat(value).isEqualTo(
            listOf(
                Pokemon(
                    id = "1",
                    name = "bulbasaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                ), Pokemon(
                    id = "2",
                    name = "ivysaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png"
                ), Pokemon(
                    id = "3",
                    name = "venusaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png"
                ), Pokemon(
                    id = "4",
                    name = "charmander",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png"
                )
            )
        )

    }

    @Test
    fun `search pokemon with id`() = runBlocking {
        homeViewModel.searchPokemon("1")
        val value = homeViewModel.shownList.getOrAwaitValueTest()
        Truth.assertThat(value).isEqualTo(
            listOf(
                Pokemon(
                    id = "1",
                    name = "bulbasaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                )
            )
        )
    }

    @Test
    fun `search pokemon with name`() = runBlocking {
        homeViewModel.searchPokemon("bulbasaur")
        val value = homeViewModel.shownList.getOrAwaitValueTest()
        Truth.assertThat(value).isEqualTo(
            listOf(
                Pokemon(
                    id = "1",
                    name = "bulbasaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                )
            )
        )
    }

    @Test
    fun `sort pokemons by name`() = runBlocking {
        homeViewModel.sortEvent.value = SortEvent.ByName
        homeViewModel.sortShownList()
        val value = homeViewModel.shownList.getOrAwaitValueTest()
        Truth.assertThat(value).isEqualTo(
            listOf(
                Pokemon(
                    id = "1",
                    name = "bulbasaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                ), Pokemon(
                    id = "4",
                    name = "charmander",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png"
                ) , Pokemon(
                    id = "2",
                    name = "ivysaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png"
                ), Pokemon(
                    id = "3",
                    name = "venusaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png"
                )
            )
        )
    }

}