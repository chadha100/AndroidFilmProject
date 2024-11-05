package com.example.tp1application.model
data class MovieResponse(
    val page: Int = 0,
    val results: List<MovieModel> = listOf(),
    val total_pages: Int = 0,
    val total_results:Int=0
)
data class MovieModel(
    val adult: Boolean,
    val backdrop_path: String?,
    val genre_ids: List<Int> = emptyList(),
    val genres: List<Genre> = emptyList(),
    val belongs_to_collection: Any? = null,
    val budget: Int = 0,
    val homepage: String? = null,
    val id: Int,
    val imdb_id: String? = null,
    val origin_country: List<String> = emptyList(),
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val production_companies: List<ProductionCompany> = emptyList(),
    val production_countries: List<ProductionCountry> = emptyList(),
    val release_date: String,
    val revenue: Int = 0,
    val runtime: Int = 0,
    val spoken_languages: List<SpokenLanguage> = emptyList(),
    val status: String? = null,
    val tagline: String? = null,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    val media_type: String? = null
)
data class Genre(
    val id: Int,
    val name: String
)
data class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)
data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)
data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)