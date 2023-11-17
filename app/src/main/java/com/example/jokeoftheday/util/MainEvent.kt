package com.example.jokeoftheday.util

sealed class MainEvent{
    object onGetJokeEvent: MainEvent()
    data class onAutoSaveChangeEvent(val state: Boolean): MainEvent()
}
