package com.example.retrofitdemo.applications.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitdemo.R
import com.example.retrofitdemo.adapters.GithubUserAdapter
import com.example.retrofitdemo.models.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    val presenter: MainPresenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listUsers.layoutManager = LinearLayoutManager(this)
        listUsers.itemAnimator = DefaultItemAnimator()

        searchUsers.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                presenter.searchUser(text)
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                return false
            }
        })
    }

    override fun setAdapterData(items: List<User>) {
        listUsers.adapter = GithubUserAdapter(this, items)
    }
}

