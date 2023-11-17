package com.example.jokeoftheday.domain.usecases

import com.example.jokeoftheday.domain.model.Joke
import com.google.common.truth.Truth.assertThat
import org.junit.Test


class ValidJokeUseCaseTest{

    private val validJokeUseCase = ValidJokeUseCase()

    @Test
    fun `empty field returns false`(){
        val result = validJokeUseCase(Joke(""))
        assertThat(result).isFalse()
    }

    @Test
    fun `default test return false`(){
        val result  = validJokeUseCase(Joke("Unable to connect server"))
        assertThat(result).isFalse()
    }

    @Test
    fun `random joke returns true`(){
        val result = validJokeUseCase(Joke("any random joke here"))
        assertThat(result).isTrue()
    }
}