package com.example.jokeoftheday.domain.usecases

import com.example.jokeoftheday.domain.model.Joke
import com.example.jokeoftheday.domain.model.SavedJoke

class ValidJokeUseCase {
    operator fun invoke(joke: Joke): Boolean{
        if (joke.content == "") return false
        if (joke.content == "Unable to connect server") return false
        return true
    }
}