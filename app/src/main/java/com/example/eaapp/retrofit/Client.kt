package com.example.eaapp.retrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


internal interface Client {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key")apiKey: String): Call<MoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key")apiKey: String): Call<MoviesResponse>

    companion object Factory {
        val BASE_URL = "https://api.themoviedb.org/3/"
        fun create(): Client {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(Client::class.java)
        }
    }
}