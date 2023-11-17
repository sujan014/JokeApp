package com.example.jokeoftheday.presentation.viewmodel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.room.Delete
import com.example.jokeoftheday.domain.model.SavedJoke
import com.example.jokeoftheday.util.JokeListEvent

@Composable
fun JokeList(
    topPadding: Dp,
    jokeListViewModel: JokeListViewModel = hiltViewModel()
){
    var jokeList = jokeListViewModel.jokeList.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = topPadding)
            .background(color = Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            LazyColumn {
                items(jokeList.value) { savedItem ->
                    JokePanel(
                        savedJoke = savedItem,
                        onDelete = {
                            jokeListViewModel.onJokeListEvent(JokeListEvent.onDeleteJoke(savedItem))
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Button(
                onClick = {
                jokeListViewModel.onJokeListEvent(JokeListEvent.onRefresh)
            }) {
                Text(
                    text = "Refresh",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(10.dp)
                )
            }
            Button(onClick = {
                jokeListViewModel.onJokeListEvent(JokeListEvent.onClearList)
            }) {
                Text(
                    text = "Clear List",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}

@Composable
fun JokePanel(
    savedJoke: SavedJoke,
    onDelete: ()-> Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth(0.94f)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = Color.Yellow)
            .padding(vertical = 10.dp, horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = savedJoke.content,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.SemiBold,
                maxLines = 10
            )
            IconButton(
                onClick = {
                    onDelete()
                },
                modifier = Modifier.align(Alignment.End)
            ) {
               Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete from repository")
            }
        }
    }
}