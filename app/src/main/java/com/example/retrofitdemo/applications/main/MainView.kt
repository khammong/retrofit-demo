package com.example.retrofitdemo.applications.main

import com.example.retrofitdemo.models.User

interface MainView {
    fun setAdapterData(items: List<User>)
}