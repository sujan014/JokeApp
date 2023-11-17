package com.example.jokeoftheday.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeoftheday.domain.model.SavedJoke
import com.example.jokeoftheday.domain.repository.SavedJokeRepository
import com.example.jokeoftheday.util.JokeListEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeListViewModel @Inject constructor(
    private val repository: SavedJokeRepository
) : ViewModel(){

    var jokeList: Flow<List<SavedJoke>> = repository.getAllSavedJokes()

    fun onJokeListEvent(event: JokeListEvent){
        when(event){
            JokeListEvent.onClearList -> {
                viewModelScope.launch {
                    repository.deleteAllSavedJokes()
                }
            }
            is JokeListEvent.onDeleteJoke -> {
                viewModelScope.launch {
                    repository.deleteSavedJoke(event.savedJoke)
                }
            }
            JokeListEvent.onRefresh -> {
                jokeList = repository.getAllSavedJokes()
            }
        }
    }
}