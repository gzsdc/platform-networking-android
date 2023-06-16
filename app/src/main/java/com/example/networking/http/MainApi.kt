package com.example.networking.http

import com.example.networking.bean.TestData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*



interface MainApi {

    @GET("posts")
    fun getHomeBanner(): Observable<List<TestData>>
}