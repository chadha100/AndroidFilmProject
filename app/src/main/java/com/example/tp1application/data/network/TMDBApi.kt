package com.example.tp1application.data.network
import com.example.tp1application.model.ActorModel
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
    @GET("movie/{id}/credits")
    suspend fun movieCredits(@Path("id")movieId: String, @Query("api_key")apiKey: String): ActorResponse

    //acteurs
    @GET("trending/person/week")
    suspend fun lastActors(@Query("api_key") apikey: String):ActorResponse
    @GET("search/person")
    suspend fun actorsKeyWord(@Query("api_key")apikey: String,@Query("query") motcle: String) : ActorResponse
    @GET("person/{actor_id}")
    suspend fun actorInfo(
        @Path("actor_id") actorId: String,@Query("api_key") apiKey: String): ActorModel
    //series
    @GET("trending/tv/week")
    suspend fun lastSeries(@Query("api_key")apikey: String) : SerieResponse
    @GET("search/tv")
    suspend fun seriesKeyWord(@Query("api_key") apiKey: String,@Query("query") query: String): SerieResponse
    @GET ("tv/{id}")
    suspend fun serieInfo (@Path("id") id: String, @Query("api_key")apikey: String, @Query("append_to_response")append_to_response:String):SerieModel
}