package com.example.pokedexapp.presentation.view.detail

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.example.pokedexapp.R
import com.example.pokedexapp.domain.model.Pokemon
import com.example.pokedexapp.launchFragmentInHiltContainer
import com.example.pokedexapp.presentation.MainFragmentFactory
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
class DetailFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    private lateinit var navController: NavController
    private lateinit var pokemonList: DetailPokemonListData
    private lateinit var pokemonPosition: String

    @Before
    fun setup() {
        hiltRule.inject()

        navController = Mockito.mock(NavController::class.java)

        pokemonPosition = "1"
        pokemonList = DetailPokemonListData(
            listOf(
                Pokemon(
                    id = "1",
                    name = "bulbasaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                ),
                Pokemon(
                    id = "2",
                    name = "ivysaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png"
                ),
                Pokemon(
                    id = "3",
                    name = "venusaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png"
                )
            )
        )

        val args = Bundle().apply {
            putString("pokemonPosition", pokemonPosition)
            putSerializable("pokemonList", pokemonList)
        }

        launchFragmentInHiltContainer<DetailFragment>(
            factory = fragmentFactory,
            fragmentArgs = args
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }
    }

    @Test
    fun testNavigationFromDetailToRightDetail() {
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.detailToRightImageView)).perform(click())
        Mockito.verify(navController).navigate(
            DetailFragmentDirections.actionDetailFragmentSelf(
                (pokemonPosition.toInt() + 1).toString(),
                pokemonList
            )
        )

    }

    @Test
    fun testNavigationFromDetailToLeftDetail() {
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.detailToLeftImageView)).perform(click())
        Mockito.verify(navController).navigate(
            DetailFragmentDirections.actionDetailFragmentSelf(
                (pokemonPosition.toInt() - 1).toString(),
                pokemonList
            )
        )

    }

    @Test
    fun testNavigationFromMostLeftDetailToLeftDetail() {
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.detailToLeftImageView)).perform(click())
        Mockito.verify(navController, Mockito.times(0)).navigate(
            DetailFragmentDirections.actionDetailFragmentSelf(
                (-1).toString(),
                pokemonList
            )
        )

    }

    @Test
    fun testNavigationFromMostRightDetailToRightDetail() {
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.detailToLeftImageView)).perform(click())
        Mockito.verify(navController, Mockito.times(0)).navigate(
            DetailFragmentDirections.actionDetailFragmentSelf(
                pokemonList.pokemonList.size.toString(),
                pokemonList
            )
        )

    }

    @Test
    fun testNavigationSwipeToRightForLeft() {
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.PokemonDetailImage)).perform(swipeRight())
        Mockito.verify(navController).navigate(
            DetailFragmentDirections.actionDetailFragmentSelf(
                (pokemonPosition.toInt() - 1).toString(),
                pokemonList
            )
        )

    }

    @Test
    fun testNavigationSwipeToLeftForRight() {
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.PokemonDetailImage)).perform(swipeLeft())
        Mockito.verify(navController).navigate(
            DetailFragmentDirections.actionDetailFragmentSelf(
                (pokemonPosition.toInt() + 1).toString(),
                pokemonList
            )
        )

    }

    @Test
    fun testOnBackPressed() {

        Espresso.pressBack()
        Mockito.verify(navController).popBackStack(R.id.homeFragment, false)

    }

    @Test
    fun testOnBackButtonPressed() {

        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.detailOnBackImageView)).perform(click())
        Mockito.verify(navController).popBackStack(R.id.homeFragment, false)

    }

}