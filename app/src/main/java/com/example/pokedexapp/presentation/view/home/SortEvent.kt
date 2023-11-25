package com.example.pokedexapp.presentation.view.home

sealed class SortEvent {
    data object ByName: SortEvent()
    data object ByID: SortEvent()
}