package com.example.jokeoftheday.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AboutPage(topPadding: Dp){
    val implementations = listOf<String>(
        "MVVM",
        "DI using Dagger Hilt",
        "Http client GET",
        "Navigation compose",
        "Room database",
        "Use-caseTest"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = topPadding)
            .padding(start = 20.dp, end = 10.dp),
        horizontalAlignment = Alignment.Start
    ){
        Column(
            modifier = Modifier.weight(1f)
        ) {
            //Spacer(modifier = Modifier.height(paddingValues.dp))
            Text("HTTP method: KTOR")
            Spacer(modifier = Modifier.height(10.dp))
            Text("Jokes website:")
            Text(
                text = "https://geek-jokes.sameerkumar.website/api?format=json",
                fontStyle = FontStyle.Italic
            )
            var cnt = 0
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                "List of features included:",
            )

            LazyColumn(){
                items(implementations){item->
                    Text(text = "${implementations.indexOf(item) + 1} $item")
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                buildAnnotatedString {
                    append("Developed by  ")
                    withStyle(
                        SpanStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                        )
                    ) {
                        append("Sujan Raj Shrestha")
                    }
                }, modifier = Modifier.padding(start = 10.dp)
            )
            Text("sujanrshr@gmail.com")
        }
    }
}