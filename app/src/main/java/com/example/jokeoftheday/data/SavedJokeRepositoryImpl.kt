package com.example.jokeoftheday.data

import com.example.jokeoftheday.domain.model.SavedJoke
import com.example.jokeoftheday.domain.repository.SavedJokeRepository
import kotlinx.coroutines.flow.Flow

class SavedJokeRepositoryImpl(
    private val savedJokeDao: SavedJokeDao
) : SavedJokeRepository {
    override fun getAllSavedJokes(): Flow<List<SavedJoke>> {
        return savedJokeDao.getAllSavedJokes()
    }

    override suspend fun getSavedJokesById(id: Int): SavedJoke? {
        return savedJokeDao.getSavedJokesById(id = id)
    }

    override suspend fun insertJoke(saveJoke: SavedJoke) {
        savedJokeDao.insertJoke(saveJoke)
    }

    override suspend fun deleteSavedJoke(savedJoke: SavedJoke) {
        savedJokeDao.deleteSavedJoke(savedJoke)
    }

    override suspend fun deleteAllSavedJokes() {
        savedJokeDao.deleteAllSavedJokes()
    }
}