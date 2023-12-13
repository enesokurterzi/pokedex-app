package com.example.pokedexapp.presentation.view.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.example.pokedexapp.R
import com.example.pokedexapp.data.repository.FakePokemonRepositoryImplTest
import com.example.pokedexapp.domain.model.Pokemon
import com.example.pokedexapp.launchFragmentInHiltContainer
import com.example.pokedexapp.presentation.MainFragmentFactory
import com.example.pokedexapp.presentation.MainFragmentFactory_Factory
import com.example.pokedexapp.presentation.view.detail.DetailFragmentDirections
import com.example.pokedexapp.presentation.view.detail.DetailPokemonListData
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class HomeFragmentTest {

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    private lateinit var navController: NavController
    private lateinit var testHomeViewModel: HomeViewModel

    private val pokemonList = listOf(
        Pokemon(
            id = "1",
            name = "bulbasaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        ),
        Pokemon(
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

    @Before
    fun setup() {
        hiltRule.inject()

        testHomeViewModel = HomeViewModel(FakePokemonRepositoryImplTest())
        navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<HomeFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
            homeViewModel = testHomeViewModel
            homeListAdapter.pokemons = pokemonList
        }
    }

    @Test
    fun testNavigationFromHomeToDetail() {

        Espresso.onView(ViewMatchers.withId(R.id.pokemonListRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<HomeListAdapter.HomeListViewHolder>(
                0,
                click()
            )
        )

        Mockito.verify(navController).navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                "0",
                DetailPokemonListData(pokemonList)
            )
        )

    }
}