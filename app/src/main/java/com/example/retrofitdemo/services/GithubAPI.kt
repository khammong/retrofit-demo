package com.example.retrofitdemo.services

import com.example.retrofitdemo.models.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubAPI {
    @GET("search/users")
    fun searchUsers(@Query("q") q: String?): Observable<UserResponse>
}