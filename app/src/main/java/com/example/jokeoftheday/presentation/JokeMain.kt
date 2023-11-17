package com.example.jokeoftheday.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jokeoftheday.presentation.viewmodel.MainViewModel
import com.example.jokeoftheday.util.MainEvent

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun JokeMain(
    topPadding: Dp,
    mainViewModel: MainViewModel = hiltViewModel()
){
    var reloadAnimation: Boolean by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = topPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = Color.LightGray)
                    .padding(10.dp)

            ) {
                if (reloadAnimation)
                {
                    AnimatedContent(
                        targetState = mainViewModel._latestJoke.content,
                        transitionSpec = {
                            fadeIn(animationSpec = tween(220, delayMillis = 90)) +
                                    scaleIn(initialScale = 0.92f, animationSpec = tween(220, delayMillis = 90)) with
                                    fadeOut(animationSpec = tween(90))
                            //slideInVertically { it } with slideOutVertically { -it }
                        },
                    ) { animateText ->

                        Text(
                            text = animateText, //mainViewModel._latestJoke.content,
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier
                                .fillMaxSize()
                                .animateContentSize()
                        )
                    }
                } else{
                    Text(
                        text = mainViewModel._latestJoke.content,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }
        BottomBar(
            onAutoSaveChange = {saveState: Boolean ->
                mainViewModel.onMainEvent(MainEvent.onAutoSaveChangeEvent(saveState))
            },
            viewModel = mainViewModel,
            newJoke = {
                reloadAnimation = it
                // this is a callback mechanism where value from BottomBar is passed to this function
            }
        )
    }
}

@Composable
fun BottomBar(
    onAutoSaveChange: (Boolean) -> Unit,
    viewModel: MainViewModel,
    newJoke:(Boolean) -> Unit
){
    var interactionSource by remember { mutableStateOf(MutableInteractionSource()) }
    val isPressed by interactionSource.collectIsPressedAsState()
    val bgndcolor = if (isPressed) Color.Blue       // not working but why ?
    else    Color.Green
    val fgndcolor = if (isPressed) Color.Black
    else    Color.Red

    var expandedState by remember { mutableStateOf(false) }
    val iconRotation by animateFloatAsState(
        targetValue = if (!expandedState) 0f else 180f
    )
    var checkState by remember { mutableStateOf(viewModel.autoSave) }

    Column{

        if (expandedState) {
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth(0.9f)
                    .background(Color.LightGray)
                    .padding(vertical = 10.dp, horizontal = 20.dp)
            ){
                Checkbox(
                    checked = checkState,
                    onCheckedChange = {
                        checkState = it
                        onAutoSaveChange(it)
                    }
                )
                Text(
                    text = "Auto save new joke."
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 10.dp)
        ) {
            IconButton(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .background(Color.LightGray)
                    .rotate(iconRotation),
                onClick = {
                    expandedState = !expandedState
                    newJoke(false)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Options"
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier, onClick = {
                    newJoke(true)
                    //onNextJoke()
                    viewModel.onMainEvent(MainEvent.onGetJokeEvent)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = bgndcolor,
                    contentColor = fgndcolor
                )
            ) {
                Text("Next Joke")

            }
        }
    }
}