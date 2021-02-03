package com.example.retrofitdemo.applications.main

import com.example.retrofitdemo.models.UserResponse
import com.example.retrofitdemo.services.GithubAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainPresenter(private val view: MainView) {

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun searchUser(q: String?) {
        retrofit.create<GithubAPI>(GithubAPI::class.java)
            .searchUsers(q)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<UserResponse> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(userResponse: UserResponse) {
                    view.setAdapterData(userResponse.users)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
    }

}