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

    @GET("tv/{tv_id}?append_to_response=credits")
    suspend fun getSerieDetails(@Path("tv_id") serieID: String, @Query("api_key") apiKey: String, @Query("language") language: String) : SerieDetails

    @GET("person/{person_id}?append_to_response=credits")
    suspend fun getActorDetails(@Path("person_id") actorID: String, @Query("api_key") apiKey: String, @Query("language") language: String) : ActorDetails

    @GET("search/movie")
    suspend fun searchMovie(@Query("api_key") apikey : String, @Query("language") language: String, @Query("query") query: String): Films

    @GET("search/tv")
    suspend fun searchSerie(@Query("api_key") apikey : String, @Query("language") language: String, @Query("query") query: String): Series

    @GET("search/person")
    suspend fun searchActor(@Query("api_key") apikey : String, @Query("language") language: String, @Query("query") query: String): Actors
}