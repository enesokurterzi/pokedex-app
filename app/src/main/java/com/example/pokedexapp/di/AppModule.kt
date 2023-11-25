package com.example.pokedexapp.di

import com.example.pokedexapp.data.remote.PokemonApi
import com.example.pokedexapp.data.repository.PokemonRepositoryImpl
import com.example.pokedexapp.domain.use_case.GetAllPokemonsUseCase
import com.example.pokedexapp.domain.use_case.GetPokemonAboutByIDUseCase
import com.example.pokedexapp.domain.use_case.GetPokemonByIDUseCase
import com.example.pokedexapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokemonApi(): PokemonApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providePokemonRepositoryImpl(
        getAllPokemonsUseCase: GetAllPokemonsUseCase,
        getPokemonByIDUseCase: GetPokemonByIDUseCase,
        getPokemonAboutByIDUseCase: GetPokemonAboutByIDUseCase
    ): PokemonRepositoryImpl {
        return PokemonRepositoryImpl(
            getAllPokemonsUseCase,
            getPokemonByIDUseCase,
            getPokemonAboutByIDUseCase
        )
    }


}