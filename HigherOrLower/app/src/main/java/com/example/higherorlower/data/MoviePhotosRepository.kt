package com.example.higherorlower.data

import com.example.higherorlower.model.MoviePhoto
import com.example.higherorlower.network.MovieApi

interface MoviePhotosRepository {
    suspend fun getMoviePhotos(): List<MoviePhoto>
}


class NetworkMoviePhotosRepository() : MoviePhotosRepository {
    override suspend fun getMoviePhotos(): List<MoviePhoto> {
        return MovieApi.retrofitService.getMovies()
    }
}