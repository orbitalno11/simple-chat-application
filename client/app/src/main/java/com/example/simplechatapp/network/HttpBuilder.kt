package com.example.simplechatapp.network

import com.example.simplechatapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HttpBuilder {
    private val baseUrl = BuildConfig.SERVER_URL
    private val apiVersion = BuildConfig.API_VERSION

    private fun getBaseUrl(): String = "$baseUrl/$apiVersion/"

    fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClient())
            .build()
    }

    private fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                val original = it.request()
                val request = original.newBuilder()
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJlNjM4MzY1Ny0wZWM0LTQ3Y2MtOTYzYi0yOWRkNTlmOTQzNTciLCJpZCI6Ijlxd3I5MTd3eG5jUGUwdU0xMHdlIiwicHJvZmlsZSI6eyJsYXN0bmFtZSI6IuC5gOC4quC4t-C4reC5hOC4p-C4ouC5jCIsImttdXR0X2lkIjoiNjAwOTA1MDA0MjgiLCJwaWN0dXJlIjoiaHR0cHM6Ly9maXJlYmFzZXN0b3JhZ2UuZ29vZ2xlYXBpcy5jb20vdjAvYi93aXNobGlzaC1wcm9qZWN0LmFwcHNwb3QuY29tL28vdXNlcnMlMkZbb2JqZWN0IE9iamVjdF0lMkZ3ZXZlLWZvcmdvdHRlbi10aGUtc2ltcGxlLWJ1dC1zdHJvbmctbGVzc29uLW1ld3R3by10cmllZC10by10ZWFjaC11cy0yMC15ZWFycy1hZ28uanBnXzE2NDM3MjExMjY5Mjg_YWx0PW1lZGlhIiwiZW1haWwiOiJzaXR0aGlub25pdEBnbWFpbC5jb20iLCJmaWVsZCI6IuC4p-C4tOC4l-C4ouC4suC4geC4suC4o-C4hOC4reC4oeC4nuC4tOC4p-C5gOC4leC4reC4o-C5jOC4m-C4o-C4sOC4ouC4uOC4geC4leC5jCIsInN0YXR1cyI6InN0dWRlbnQiLCJmaXJzdG5hbWUiOiLguKrguLTguJfguJjguLTguJnguJnguJfguYwifSwiaWF0IjoxNjQ2NjcyNzU5LCJleHAiOjE2NDY2ODcxNTl9.thVoDwulEs1qk4PemG1MTCotRJ3E92F5EccQJ4hgWMY")
                    .build()
                it.proceed(request)
            }.build()
    }
}