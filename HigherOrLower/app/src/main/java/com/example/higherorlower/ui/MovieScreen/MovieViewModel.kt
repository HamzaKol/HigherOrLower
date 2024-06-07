package com.example.higherorlower.ui.MovieScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.higherorlower.data.NetworkMoviePhotosRepository
import com.example.higherorlower.model.MoviePhoto
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MovieUiState {
    data class Success(val photos: List<MoviePhoto>) : MovieUiState
    object Error : MovieUiState
    object Loading : MovieUiState
}

class MovieViewModel(
    private val moviePhotosRepository: NetworkMoviePhotosRepository = NetworkMoviePhotosRepository()
) : ViewModel() {
    var movieUiState: MovieUiState by mutableStateOf(MovieUiState.Loading)
        private set

    var photo1 by mutableStateOf(MoviePhoto(
        rank = 0,
        title = "",
        description = "",
        image = "",
        big_image = "",
        genre = listOf("", ""),
        thumbnail = "",
        rating = "",
        id = "",
        year = 0,
        imdbid = "",
        imdb_link = ""
    ))
        private set

    var photo2 by mutableStateOf(MoviePhoto(
        rank = 0,
        title = "",
        description = "",
        image = "",
        big_image = "",
        genre = listOf("", ""),
        thumbnail = "",
        rating = "",
        id = "",
        year = 0,
        imdbid = "",
        imdb_link = ""
    ))
        private set

    init {
        getMoviePhotos()
    }

    private fun getMoviePhotos() {
        viewModelScope.launch {
            movieUiState = try {
                val photos = moviePhotosRepository.getMoviePhotos()
                photo1 = photos[photos.indices.random()]
                photo2 = photos[photos.indices.random()]
                MovieUiState.Success(photos)
            } catch (e: IOException) {
                MovieUiState.Error
            } catch (e: HttpException) {
                MovieUiState.Error
            }
        }
    }
    fun updateVariablesOnClick(photos: List<MoviePhoto>) {
        photo1 = photo2
        photo2 = photos[photos.indices.random()]
    }
}
