package com.example.tp1application.data.network
import com.example.tp1application.model.ActorResponse
import com.example.tp1application.model.MovieModel
import com.example.tp1application.model.MovieResponse
import com.example.tp1application.model.SerieModel
import com.example.tp1application.model.SerieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
interface TMDBApi {
    //movies
    @GET("search/movie")
    suspend fun movieKeyWord(@Query("api_key")apikey: String,@Query("query") motcle: String) : MovieResponse
    @GET("trending/movie/week")
    suspend fun lastMovies(@Query("api_key")apikey: String) : MovieResponse
    @GET ("movie/{id}")
    suspend fun movieInfo (@Path("id") id: String, @Query("api_key")apikey: String, @Query("append_to_response")append_to_response:String):MovieModel
    //acteurs
    @GET("trending/person/week")
    suspend fun lastActors(@Query("api_key") apikey: String):ActorResponse
    @GET("search/person")
    suspend fun actorsKeyWord(@Query("api_key")apikey: String,@Query("query") motcle: String) : ActorResponse
    //series
    @GET("trending/tv/week")
    suspend fun lastSeries(@Query("api_key")apikey: String) : SerieResponse
    @GET("search/tv")
    suspend fun seriesKeyWord(@Query("api_key")apikey: String,@Query("query") motcle: String) : SerieResponse
    @GET ("tv/{id}")
    suspend fun serieInfo (@Path("id") id: String, @Query("api_key")apikey: String, @Query("append_to_response")append_to_response:String):SerieModel
}