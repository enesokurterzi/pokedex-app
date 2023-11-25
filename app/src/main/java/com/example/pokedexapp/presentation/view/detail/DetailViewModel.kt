package com.example.pokedexapp.presentation.view.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.domain.model.PokemonAbout
import com.example.pokedexapp.domain.model.PokemonDetail
import com.example.pokedexapp.domain.repository.PokemonRepository
import com.example.pokedexapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    val pokemonDetail = MutableLiveData<PokemonDetail?>()
    val pokemonAbout = MutableLiveData<PokemonAbout?>()
    val isLoading = MutableLiveData<Boolean>()

    fun loadData(pokemonID: String) {
        loadDetailData(pokemonID)
        loadAboutData(pokemonID)
    }

    private fun loadDetailData(pokemonID: String) {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = pokemonRepository.getPokemonByID(pokemonID)) {
                is Resource.Success -> {
                    withContext(Dispatchers.Main) {
                        pokemonDetail.value = result.data
                        delay(500)
                        isLoading.value = false
                    }
                }

                is Resource.Error -> {

                }
            }

        }
    }

    private fun loadAboutData(pokemonID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = pokemonRepository.getPokemonAboutByID(pokemonID)) {
                is Resource.Success -> {
                    withContext(Dispatchers.Main) {
                        pokemonAbout.value = result.data
                    }
                }

                is Resource.Error -> {

                }
            }

        }

    }

}