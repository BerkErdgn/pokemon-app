package com.berkerdgn.pokemon_app.dependincyInjection

import android.content.Context
import androidx.room.Room
import com.berkerdgn.pokemon_app.R
import com.berkerdgn.pokemon_app.api.PokemonApi
import com.berkerdgn.pokemon_app.for_room.room_db.PokemonDao
import com.berkerdgn.pokemon_app.for_room.room_db.PokemonDataBase
import com.berkerdgn.pokemon_app.repo.PokemonRepository
import com.berkerdgn.pokemon_app.repo.PokemonRepositoryImpl
import com.berkerdgn.pokemon_app.util.Util.BASE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //for general
    @Singleton
    @Provides
    fun providePokemonRepository(pokemonApi: PokemonApi, pokemonDao: PokemonDao):PokemonRepository {
        return  PokemonRepositoryImpl(pokemonApi = pokemonApi, pokemonDao = pokemonDao)
    }

    //for api
    @Singleton
    @Provides
    fun providePokemonApi(): PokemonApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }



    //for images
    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )


    //for Dao

    @Singleton
    @Provides
    fun injectPokemonDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,PokemonDataBase::class.java,"savedPokemons"
    ).allowMainThreadQueries().build()


    @Singleton
    @Provides
    fun injectDao(dataBase: PokemonDataBase) = dataBase.pokemonDao()

}