package com.example.jokeoftheday.data

import com.example.jokeoftheday.domain.model.Joke
import com.example.jokeoftheday.domain.repository.JokeRepository
import com.example.jokeoftheday.util.HttpRoutes
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class JokeRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
): JokeRepository {
    override suspend fun getJoke(): Joke {
        return try{
            httpClient.get(HttpRoutes.BASE_URL)
        } catch (e: Exception){
            e.printStackTrace()
            Joke("Unable to connect server")
        }
    }
}