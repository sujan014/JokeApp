package com.example.jokeoftheday.domain.repository

import com.example.jokeoftheday.domain.model.Joke

interface JokeRepository {

    suspend fun getJoke(): Joke

}