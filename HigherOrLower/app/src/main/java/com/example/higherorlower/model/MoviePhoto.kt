package com.example.higherorlower.model

import kotlinx.serialization.Serializable

@Serializable
data class MoviePhoto(
    val rank: Int,
    val title: String,
    val description: String,
    val image: String,
    val big_image: String,
    val genre: List<String>,
    val thumbnail: String,
    val rating: String,
    val id: String,
    val year: Int,
    val imdbid: String,
    val imdb_link: String
)
