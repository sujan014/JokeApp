package com.example.jokeoftheday.data

import androidx.room.*
import com.example.jokeoftheday.domain.model.SavedJoke
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedJokeDao {

    @Query("SELECT * FROM SavedJoke")
    fun getAllSavedJokes(): Flow<List<SavedJoke>>
    // when returning flow list, it must not be done in suspend. Or else error.

    @Query("SELECT * FROM SavedJoke where id = :id")
    suspend fun getSavedJokesById(id: Int): SavedJoke?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoke(joke: SavedJoke)

    @Delete
    suspend fun deleteSavedJoke(joke: SavedJoke)

    @Query("DELETE FROM SavedJoke")
    suspend fun deleteAllSavedJokes()
}
