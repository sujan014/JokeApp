package com.example.jokeoftheday.domain.repository

import com.example.jokeoftheday.domain.model.SavedJoke
import kotlinx.coroutines.flow.Flow

interface SavedJokeRepository {

    fun getAllSavedJokes(): Flow<List<SavedJoke>>

    suspend fun getSavedJokesById(id: Int): SavedJoke?

    suspend fun insertJoke(joke: SavedJoke)

    suspend fun deleteSavedJoke(joke: SavedJoke)

    suspend fun deleteAllSavedJokes()

}