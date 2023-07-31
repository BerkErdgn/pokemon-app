package com.berkerdgn.pokemon_app.dependincyInjection

import com.berkerdgn.pokemon_app.api.PokemonApi
import com.berkerdgn.pokemon_app.repo.PokemonRepository
import com.berkerdgn.pokemon_app.repo.PokemonRepositoryImpl
import com.berkerdgn.pokemon_app.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePokemonRepository(pokemonApi: PokemonApi):PokemonRepository {
        return  PokemonRepositoryImpl(pokemonApi = pokemonApi)
    }

    @Singleton
    @Provides
    fun providePokemonApi(): PokemonApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }


}