package com.example.baselibs.http

import com.example.baselibs.http.constant.HttpConstant
import com.example.baselibs.http.constant.HttpConstant.BASE_URL
import com.example.baselibs.http.interceptor.HeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


abstract class RetrofitFactory<T> {

    var service: T
    private var mBaseUrl: String = ""
    private var retrofit: Retrofit? = null

    open fun baseUrl(): String = BASE_URL

    abstract fun getService(): Class<T>

    init {
        mBaseUrl = this.baseUrl()
        if (mBaseUrl.isEmpty()) {
            throw RuntimeException("base url can not be empty!")
        }
        service = getRetrofit().create(this.getService())
    }

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            synchronized(RetrofitFactory::class.java) {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(mBaseUrl)
                        .client(attachOkHttpClient())
                        .addConverterFactory(GsonConverterFactory.create())
                        //.addConverterFactory(MoshiConverterFactory.create())
                        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                        .build()
                }
            }
        }
        return retrofit!!
    }

    open fun attachOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        builder.run {
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(HeaderInterceptor())
            connectTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS) //连接超时15s
            readTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        }
        return builder.build()
    }

}