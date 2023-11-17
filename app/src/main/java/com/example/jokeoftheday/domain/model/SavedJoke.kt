package com.example.jokeoftheday.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedJoke(
    @PrimaryKey
    val id: Int? = null,
    val content: String,
)
