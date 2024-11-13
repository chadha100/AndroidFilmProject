package com.example.tp1application.model
data class ActorResponse(
    val page: Int,
    val results: List<ActorModel>,
    val results1: List<KnownFor>?,
    val total_pages: Int,
    val total_results: Int,
    val cast: List<ActorModel>?
)
data class ActorModel(
    val adult: Boolean,
    val also_known_as: List<String>?,
    val biography: String?,
    val birthday: String?,
    val deathday: Any?,
    val gender: Int?,
    val homepage: Any?,
    val id: Int,
    val imdb_id: String?,
    val known_for_department: String?,
    val name: String,
    val place_of_birth: String?,
    val popularity: Double?,
    val profile_path: String?
)
data class KnownFor(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

