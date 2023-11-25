package com.example.pokedexapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.pokedexapp.presentation.view.detail.DetailFragment
import com.example.pokedexapp.presentation.view.detail.DetailTypeAdapter
import com.example.pokedexapp.presentation.view.home.HomeFragment
import com.example.pokedexapp.presentation.view.home.HomeListAdapter
import javax.inject.Inject

class MainFragmentFactory @Inject constructor(
    private val homeListAdapter: HomeListAdapter,
    private val detailTypeAdapter: DetailTypeAdapter
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            HomeFragment::class.java.name -> HomeFragment(homeListAdapter)
            DetailFragment::class.java.name -> DetailFragment(detailTypeAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}