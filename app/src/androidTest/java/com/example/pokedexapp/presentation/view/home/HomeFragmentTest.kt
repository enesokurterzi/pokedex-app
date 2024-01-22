package com.example.pokedexapp.presentation.view.home

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
import com.example.pokedexapp.domain.use_case.GetAllPokemonsUseCase
import com.example.pokedexapp.getOrAwaitValueTest
import com.example.pokedexapp.launchFragmentInHiltContainer
import com.example.pokedexapp.presentation.MainFragmentFactory
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

    @Before
    fun setup() {
        hiltRule.inject()

        navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<HomeFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
            testHomeViewModel = homeViewModel
        }
    }

    @Test
    fun testNavigationFromHomeToDetail() {
        Thread.sleep(3000)
        val pokemonList = testHomeViewModel.shownList.getOrAwaitValueTest()!!

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