package com.example.tp1application.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp1application.data.network.TMDBApi
import com.example.tp1application.model.SerieModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.MutableStateFlow
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
    fun lastSeries(){
        viewModelScope.launch{
            series.value = tmdbapi.lastSeries(api_key).results
            }
        }
}