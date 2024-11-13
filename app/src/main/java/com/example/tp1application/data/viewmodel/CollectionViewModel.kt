package com.example.tp1application.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp1application.data.network.TMDBApi
import com.example.tp1application.model.CollectionModel
import com.example.tp1application.model.MovieModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CollectionViewModel: ViewModel() {
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val tmdbapi = retrofit.create(TMDBApi::class.java)

    val api_key = "b57151d36fecd1b693da830a2bc5766f"
    //collection
    private val _collections = MutableStateFlow<List<CollectionModel>>(listOf())//utilisé pour créer des flux de données réactifs, permettant de notifier l'interface utilisateur dès que les données changent.
    val collections: StateFlow<List<CollectionModel>> = _collections
    fun getLastColletions() {
        viewModelScope.launch {
            try {
                val response = tmdbapi.lastCollections(api_key, mot_cle = "horror")
                _collections.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}