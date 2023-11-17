package com.example.jokeoftheday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jokeoftheday.presentation.JokeMain
import com.example.jokeoftheday.presentation.MainWindow
import com.example.jokeoftheday.presentation.viewmodel.MainViewModel
import com.example.jokeoftheday.ui.theme.JokeOfTheDayTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //private val mainViewModel by viewModels<MainViewModel>()
    //val mainViewModel: MainViewModel = HiltViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokeOfTheDayTheme {
                // A surface container using the 'background' color from the theme
                //JokeMain(mainViewModel)
                //JokeMain()
                MainWindow()
            }
        }
    }
}