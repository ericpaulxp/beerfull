package com.example.beerfull.di

import android.content.Context
import androidx.room.Room
import com.example.beerfull.data.local.BeerDatabase
import com.example.beerfull.data.local.FavoriteBeerDao
import com.example.beerfull.data.remote.BeerApi
import com.example.beerfull.data.repository.BeerRepositoryImpl
import com.example.beerfull.domain.repository.BeerRepository
import com.example.beerfull.domain.use_cases.BeerUseCases
import com.example.beerfull.domain.use_cases.DeleteBeer
import com.example.beerfull.domain.use_cases.GetBeerDetails
import com.example.beerfull.domain.use_cases.GetBeers
import com.example.beerfull.domain.use_cases.GetFavoriteBeers
import com.example.beerfull.domain.use_cases.SaveBeer
import com.example.beerfull.domain.use_cases.SearchBeer
import com.example.beerfull.utils.Constants.BASE_URL
import com.example.beerfull.utils.Constants.BEER_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesBeerDatabase(
        @ApplicationContext context: Context
    ): BeerDatabase {
        return Room.databaseBuilder(
            context,
            BeerDatabase::class.java,
            BEER_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun providesFavBeerDao(
        beerDatabase: BeerDatabase
    ): FavoriteBeerDao = beerDatabase.favoriteBeerDao()

    @Provides
    @Singleton
    fun providesHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesBeerApi(okHttpClient: OkHttpClient): BeerApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesBeerRepository(
        beerApi: BeerApi,
        favoriteBeerDao: FavoriteBeerDao
    ): BeerRepository = BeerRepositoryImpl(beerApi, favoriteBeerDao)

    @Provides
    @Singleton
    fun provideBeerUseCases(
        beerRepository: BeerRepository,
    ): BeerUseCases {
        return BeerUseCases(
            getBeers = GetBeers(beerRepository),
            getBeerDetails = GetBeerDetails(beerRepository),
            searchBeer = SearchBeer(beerRepository),
            saveBeer = SaveBeer(beerRepository),
            deleteBeer = DeleteBeer(beerRepository),
            getFavoriteBeers = GetFavoriteBeers(beerRepository)

        )
    }
}