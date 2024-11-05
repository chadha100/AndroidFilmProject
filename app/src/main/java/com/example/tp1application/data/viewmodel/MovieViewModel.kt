package com.example.tp1application.data.viewmodel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp1application.data.network.TMDBApi
import com.example.tp1application.model.MovieModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
class MovieViewModel : ViewModel() {
    val moshi  = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val tmdbapi = retrofit.create(TMDBApi::class.java)

    val api_key = "b57151d36fecd1b693da830a2bc5766f"

    // Films
    private val _movies = MutableStateFlow<List<MovieModel>>(listOf())
    val movies: StateFlow<List<MovieModel>> = _movies

    // StateFlow pour les détails du film
    private val _movieDetails = MutableStateFlow<MovieModel?>(null)
    val movieDetails: StateFlow<MovieModel?> = _movieDetails

    fun getTrendingMovies() {
        viewModelScope.launch {
            try {
                val response = tmdbapi.lastMovies(api_key)
                // Trier les films par date de sortie dans l'ordre descendant
                _movies.value = response.results.sortedByDescending { it.release_date }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun SearchKeyWord(motcle: String) {
        viewModelScope.launch {
            try {
                val response = tmdbapi.movieKeyWord(api_key, motcle)
                if (response.results != null) {
                    // Trier les films par date de sortie dans l'ordre descendant
                    _movies.value = response.results.sortedByDescending { it.release_date }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Fonction pour récupérer les détails d'un film par ID
    fun getMovieDetails(movieId: String) {
        viewModelScope.launch {
            try {
                val response = tmdbapi.movieInfo(movieId, api_key, "credits,images")
                Log.d("MovieViewModel", "Movie Details Retrieved: $response")
                _movieDetails.value = response
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("MovieViewModel", "Error fetching movie details: ${e.message}")
            }
        }
    }

}
