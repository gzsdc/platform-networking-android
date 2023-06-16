package com.example.networking.http

import com.example.baselibs.http.RetrofitFactory
import com.example.baselibs.http.constant.HttpConstant

object MainRetrofit : RetrofitFactory<MainApi>() {

    override fun getService(): Class<MainApi> = MainApi::class.java

}