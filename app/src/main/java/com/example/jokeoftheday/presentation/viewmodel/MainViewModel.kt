package com.example.jokeoftheday.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeoftheday.domain.model.Joke
import com.example.jokeoftheday.domain.model.SavedJoke
import com.example.jokeoftheday.domain.repository.JokeRepository
import com.example.jokeoftheday.domain.repository.SavedJokeRepository
import com.example.jokeoftheday.domain.usecases.ValidJokeUseCase
import com.example.jokeoftheday.util.MainEvent
import com.example.jokeoftheday.util.MainEvent.onAutoSaveChangeEvent
import com.example.jokeoftheday.util.TAGS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val jokeRepository: JokeRepository,
    private val savedJokeRepository: SavedJokeRepository,
    private val validJokeUseCase: ValidJokeUseCase
): ViewModel(){

    var _latestJoke by mutableStateOf<Joke>(Joke("No Jokes"))

    //var savedList: Flow<List<SavedJoke>> = MutableStateFlow<List<SavedJoke>>(emptyList())
    var autoSave = true
    init
    {
        onGetJoke()
    }

    fun onGetJoke(){
        viewModelScope.launch {
            _latestJoke = jokeRepository.getJoke()
            if (autoSave) {
                onSaveJoke(_latestJoke)
            }
        }
    }

    private fun onSaveJoke(joke: Joke){
        viewModelScope.launch {
            if (validJokeUseCase(joke)) {
                try {
                    savedJokeRepository.insertJoke(SavedJoke(content = joke.content))
                    Log.d(TAGS, "Successfully saved to repository: \n-${joke.content}")
                } catch (e: Exception){
                    Log.d(TAGS, "Could not save to repository. \n-${e.message}: \n-${joke.content}")
                }
            } else {
                Log.d(TAGS, "Invalid content: ${joke.content}")
            }
        }
    }

    fun onMainEvent(event: MainEvent){
        when(event){
            is MainEvent.onAutoSaveChangeEvent -> {
                autoSave = event.state
            }
            MainEvent.onGetJokeEvent -> {
                onGetJoke()
            }
        }
    }

}

/*
@HiltViewModel
class MainViewModel @Inject constructor(
    private val jokeRepository: JokeRepository
): ViewModel(){

    var _latestJoke by mutableStateOf<Joke>(Joke("No Jokes"))

    init
    {
        onGetJoke()
    }

    fun onGetJoke(){
        viewModelScope.launch {
            _latestJoke = jokeRepository.getJoke()
        }
    }
}*/