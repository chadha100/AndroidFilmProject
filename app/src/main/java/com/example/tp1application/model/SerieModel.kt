package com.example.tp1application.model
data class SerieResponse(
    val page: Int,
    val results: List<SerieModel>,
    val total_pages: Int,
    val total_results: Int
)
data class SerieModel(
    val adult: Boolean,
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
)