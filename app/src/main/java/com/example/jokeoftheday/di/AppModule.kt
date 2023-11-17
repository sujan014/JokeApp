package com.example.jokeoftheday.di

import android.app.Application
import androidx.room.Room
import com.example.jokeoftheday.data.JokeRepositoryImpl
import com.example.jokeoftheday.data.SavedJokeRepositoryImpl
import com.example.jokeoftheday.data.JokeDatabase
import com.example.jokeoftheday.domain.repository.JokeRepository
import com.example.jokeoftheday.domain.repository.SavedJokeRepository
import com.example.jokeoftheday.domain.usecases.ValidJokeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // get Http client
    @Provides
    @Singleton
    fun getHttpClient(
        httpClient: JokeClient
    ): HttpClient = httpClient.getHttpClient()

    @Provides
    @Singleton
    fun getJokesRepoitory(
        repoImpl: JokeRepositoryImpl
    ): JokeRepository = repoImpl

    /**************** ROOM DI ********************/

    @Provides
    @Singleton
    fun getSavedJokeDB(app: Application): JokeDatabase {
        return Room.databaseBuilder(
            app,
            JokeDatabase::class.java,
            "Joke_database"
        ).build()
    }

    @Provides
    @Singleton
    fun getSavedJokeROOMRepository(
        db: JokeDatabase
    ): SavedJokeRepository{
        return SavedJokeRepositoryImpl(db.dao)
    }

    /**************** ROOM DI ********************/

    @Provides
    @Singleton
    fun providesValidJokeUseCase(): ValidJokeUseCase {
        return ValidJokeUseCase()
    }
}