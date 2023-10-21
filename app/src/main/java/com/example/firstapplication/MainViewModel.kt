package com.example.firstapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import androidx.lifecycle.SavedStateHandle

class MainViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val movies = MutableStateFlow<List<Film>>(listOf())
    val series = MutableStateFlow<List<Serie>>(listOf())
    val actors = MutableStateFlow<List<Actor>>(listOf())
    val movieDetails = MutableStateFlow<FilmDetails>(FilmDetails())
    val serieDetails = MutableStateFlow<SerieDetails>(SerieDetails())
    val actorDetails = MutableStateFlow<ActorDetails>(ActorDetails())
    private val movieID: String? = savedStateHandle["movieID"]
    private val serieID: String? = savedStateHandle["serieID"]
    private val actorID: String? = savedStateHandle["actorID"]

    val apikey = "aee96c348833f50018bc4653952aa9af"

    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(Tmdb::class.java)

    fun getTrendingMovies() {
        viewModelScope.launch {
            movies.value = service.getTrendingMovies(apikey, "fr").results
        }
    }
    fun getTrendingSeries() {
        viewModelScope.launch {
            series.value = service.getTrendingSeries(apikey, "fr").results
        }
    }
    fun getTrendingActors() {
        viewModelScope.launch {
            actors.value = service.getTrendingActors(apikey, "fr").results
        }
    }
    fun getFilmDetails() {
        viewModelScope.launch {
            movieDetails.value = service.getFilmDetails(movieID?:"", apikey, "fr")
        }
    }
    fun getSerieDetails() {
        viewModelScope.launch {
            serieDetails.value = service.getSerieDetails(serieID?:"", apikey, "fr")
        }
    }
    fun getActorDetails() {
        viewModelScope.launch {
            actorDetails.value = service.getActorDetails(actorID?:"", apikey, "fr")
        }
    }
}