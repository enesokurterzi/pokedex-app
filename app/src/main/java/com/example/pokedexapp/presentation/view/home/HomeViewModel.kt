package com.example.pokedexapp.presentation.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.domain.model.Pokemon
import com.example.pokedexapp.domain.repository.PokemonRepository
import com.example.pokedexapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    private val pokemonList = MutableLiveData<List<Pokemon>?>()
    val pokemonError = MutableLiveData<String?>()
    val shownList = MutableLiveData<MutableList<Pokemon>?>()
    private var currentList: List<Pokemon>? = emptyList()
    val sortEvent: MutableLiveData<SortEvent> = MutableLiveData(SortEvent.ByID)
    private var startIndex = 0
    private val groupSize = 50
    private var endIndex = 0

    init {
        loadAll()
    }

    private fun loadAll() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = pokemonRepository.getAllPokemons()) {
                is Resource.Success -> {
                    withContext(Dispatchers.Main) {
                        pokemonList.value = result.data
                        currentList = pokemonList.value
                        endIndex = 0
                        loadShownList()
                        sortShownList()
                    }

                }

                is Resource.Error -> {
                    withContext(Dispatchers.Main) {
                        pokemonError.value = result.message
                    }
                }
            }
        }
    }

    fun searchPokemon(searchText: String) {
        val searchedList =
            pokemonList.value?.filter { it.id.contains(searchText) || it.name.contains(searchText) }
        if (!searchedList.isNullOrEmpty()) {
            pokemonError.value = null
            currentList = searchedList
            endIndex = 0
            loadShownList()
            sortShownList()
        } else {
            if (pokemonError.value.isNullOrEmpty()) {
                pokemonError.value = "There is no pokemon found."
            }
        }
    }

    fun loadShownList() {
        val currentListHere = currentList
        if (currentListHere != null) {
            endIndex += groupSize
            if (endIndex <= currentListHere.size) {
                val newList = currentListHere.subList(startIndex, endIndex)
                shownList.value = newList.toMutableList()
            } else {
                endIndex = currentListHere.size
                val newList = currentListHere.subList(startIndex, endIndex)
                shownList.value = newList.toMutableList()
            }
        }
    }

    fun sortShownList() {
        currentList = when (sortEvent.value) {
            is SortEvent.ByID -> {
                currentList?.sortedBy { it.id.toInt() }
            }

            is SortEvent.ByName -> {
                currentList?.sortedBy { it.name }
            }
            else -> {
                currentList?.sortedBy { it.id.toInt() }
            }
        }
        loadShownList()
    }

}