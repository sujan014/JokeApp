package com.example.jokeoftheday.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jokeoftheday.domain.model.SavedJoke

@Database(
    entities = [SavedJoke::class],
    version = 1
)
abstract class JokeDatabase : RoomDatabase(){

    abstract val dao: SavedJokeDao

}