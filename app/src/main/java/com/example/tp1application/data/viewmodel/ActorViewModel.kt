package com.example.tp1application.data.viewmodel
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

class ActorViewModel : ViewModel(){
    val moshi  = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val tmdbapi = retrofit.create(TMDBApi::class.java)


    val api_key ="b57151d36fecd1b693da830a2bc5766f"
    // Acteurs
     val _actors = MutableStateFlow<List<ActorModel>>(listOf())

    fun lastActors() {
        viewModelScope.launch {
            _actors.value = tmdbapi.lastActors(api_key).results
        }
    }

    fun SeachActors(motcle: String){
        viewModelScope.launch{
            _actors.value = tmdbapi.actorsKeyWord(api_key, motcle).results
            }
      }

}