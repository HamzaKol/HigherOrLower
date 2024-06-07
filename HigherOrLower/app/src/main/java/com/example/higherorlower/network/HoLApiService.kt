package com.example.higherorlower.network

import com.example.higherorlower.model.MoviePhoto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://imdb-top-100-movies.p.rapidapi.com/"
private const val API_KEY = "faf6ca866cmsh0d957edbb4e3479p181494jsn22ecdb3c1883"
private const val API_HOST = "imdb-top-100-movies.p.rapidapi.com"

// Interceptor to add the API key to requests
class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("x-rapidapi-key", API_KEY)
            .addHeader("x-rapidapi-host", API_HOST)
            .build()
        return chain.proceed(request)
    }
}

// OkHttpClient with the interceptor
private val client = OkHttpClient.Builder()
    .addInterceptor(ApiKeyInterceptor())
    .build()

// JSON configuration
private val json = Json {
    ignoreUnknownKeys = true // Handle unknown keys gracefully
    isLenient = true // Allow lenient parsing of JSON
}

private val retrofit = Retrofit.Builder()
    .client(client)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface HoLApiService {
    @GET("/")
    suspend fun getMovies(): List<MoviePhoto>
}

object MovieApi {
    val retrofitService: HoLApiService by lazy {
        retrofit.create(HoLApiService::class.java)
    }
}
