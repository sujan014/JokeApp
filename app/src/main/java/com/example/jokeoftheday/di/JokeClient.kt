package com.example.jokeoftheday.di

import android.util.Log
import com.example.jokeoftheday.domain.repository.JokeRepository
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import javax.inject.Inject

class JokeClient @Inject constructor() {

    val json = Json{
        encodeDefaults = true
        ignoreUnknownKeys = true
        isLenient = true
    }
    // will return an Http client
    fun getHttpClient(): HttpClient {
        return HttpClient(Android){
            // inside this scope ,we configure our HTTP client

            // install json feature
            install(JsonFeature){
                serializer = KotlinxSerializer(json)
            }

            install(HttpTimeout){
                socketTimeoutMillis = 30000
                requestTimeoutMillis = 30000
                connectTimeoutMillis = 30000
            }

            install(Logging){
                logger = object: Logger {
                    override fun log(message: String) {
                        Log.i(TAG_KTOR_LOGGER, "logger Value -> $message")
                    }
                }
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.i(TAG_HTTP_STATUS_LOGGER, "${response.status.value}")
                    // get Http status response code
                }
            }

            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }

    companion object {
        private const val TIME_OUT = 10_000
        private const val TAG_KTOR_LOGGER = "ktor_logger:"
        private const val TAG_HTTP_STATUS_LOGGER = "http_status:"
    }

}