package com.example.tp1application.data.viewmodel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp1application.data.network.TMDBApi
import com.example.tp1application.model.ActorModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ActorViewModel : ViewModel() {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val tmdbapi = retrofit.create(TMDBApi::class.java)

    val api_key = "b57151d36fecd1b693da830a2bc5766f"

    // Liste d'acteurs
    private val _actors = MutableStateFlow<List<ActorModel>>(listOf())
    val actors: StateFlow<List<ActorModel>> = _actors

    fun lastActors() {
        viewModelScope.launch {
            _actors.value = tmdbapi.lastActors(api_key).results
        }
    }
    // Search query
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery
    fun searchActors(motcle: String) {
        _searchQuery.value = motcle // Mettre à jour la recherche
        viewModelScope.launch {
            _actors.value = tmdbapi.actorsKeyWord(api_key, motcle).results
        }
    }

    // Détails d'un acteur
    private val _actorDetails = MutableStateFlow<ActorModel?>(null)
    val actorDetails: StateFlow<ActorModel?> = _actorDetails

    fun getActorDetails(actorId: String) {
        viewModelScope.launch {
            try {
                val response = tmdbapi.actorInfo(actorId, api_key)
                Log.d("ActorViewModel", "Actor Details Retrieved: $response")
                _actorDetails.value = response
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ActorViewModel", "Error fetching actor details: ${e.message}")
            }
        }
    }
}
