package com.example.firstapplication

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Tmdb {
    @GET("trending/movie/week")
    suspend fun getTrendingMovies(@Query("api_key") apikey : String, @Query("language") language: String): Films

    @GET("trending/tv/week")
    suspend fun getTrendingSeries(@Query("api_key") apikey : String, @Query("language") language: String): Series

    @GET("trending/person/week")
    suspend fun getTrendingActors(@Query("api_key") apikey : String, @Query("language") language: String): Actors

    @GET("movie/{movie_id}?append_to_response=credits")
    suspend fun getFilmDetails(@Path("movie_id") movieID: String, @Query("api_key") apiKey: String, @Query("language") language: String) : FilmDetails
}