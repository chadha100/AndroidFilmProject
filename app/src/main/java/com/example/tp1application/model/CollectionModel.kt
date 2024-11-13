package com.example.tp1application.model
data class CollectionModel(
    val adult: Boolean,
    val backdrop_path: String,
    val id: Int,
    val name: String,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val poster_path: String
)
data class CollectionResponse(
    val page: Int,
    val results: List<CollectionModel>,
    val total_pages: Int,
    val total_results: Int
)