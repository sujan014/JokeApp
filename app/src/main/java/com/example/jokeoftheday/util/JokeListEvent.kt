package com.example.jokeoftheday.util

import com.example.jokeoftheday.domain.model.SavedJoke

sealed class JokeListEvent{
    object onRefresh: JokeListEvent()
    data class onDeleteJoke(val savedJoke: SavedJoke): JokeListEvent()
    object onClearList: JokeListEvent()
}
