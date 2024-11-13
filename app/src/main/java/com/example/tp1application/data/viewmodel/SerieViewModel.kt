package com.example.tp1application.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp1application.data.network.TMDBApi
import com.example.tp1application.model.SerieModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class SerieViewModel: ViewModel() {
    val moshi  = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val tmdbapi = retrofit.create(TMDBApi::class.java)


    val api_key ="b57151d36fecd1b693da830a2bc5766f"
    //serie
    val series= MutableStateFlow<List<SerieModel>>(listOf())

    fun lastSeries() {
        viewModelScope.launch {
            try {
                val response = tmdbapi.lastSeries(api_key)
                Log.d("SerieViewModel", "Séries récupérées: ${response.results}") // Ajouter un log pour vérifier les données
                series.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("SerieViewModel", "Erreur lors de la récupération des séries: ${e.message}")
            }
        }
    }

    //serieDetails
// Si vous voulez gérer une liste de séries
private val _serieDetails = MutableStateFlow<List<SerieModel>>(emptyList())
    val serieDetails: StateFlow<List<SerieModel>> = _serieDetails

    fun getSerieDetails(serieId: String) {
        viewModelScope.launch {
            try {
                // Utilisez la bonne méthode pour récupérer les détails d'une série
                val response = tmdbapi.serieInfo(serieId, api_key, "credits,images")
                Log.d("SerieViewModel", "Serie Details Retrieved: $response")
                // Assurez-vous d'assigner le bon type, qui est une liste d'une seule série dans ce cas
                _serieDetails.value = listOf(response) // Convertir en liste pour correspondre au type
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("SerieViewModel", "Error fetching serie details: ${e.message}")
            }
        }
    }

    //search
    fun SearchKeyWordS(motcle: String) {
        viewModelScope.launch {
            try {
                val response = tmdbapi.seriesKeyWord(api_key, motcle)
                Log.d("Search", "Response: ${response.results}")
                val results = response.results

                if (!results.isNullOrEmpty()) {
                    _serieDetails.value = results // Mettre à jour sérieDetails avec les résultats de la recherche
                } else {
                    _serieDetails.value = emptyList()
                    Log.d("Search", "Aucun résultat trouvé pour le mot-clé : $motcle")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("Search", "Erreur lors de la récupération des résultats : ${e.message}")
            }
        }
    }

}


