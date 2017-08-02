package com.example.rr.retrofitkotlin.app.service

import com.example.rr.retrofitkotlin.app.data.model.repo.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GitHubService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<Repo>>
}