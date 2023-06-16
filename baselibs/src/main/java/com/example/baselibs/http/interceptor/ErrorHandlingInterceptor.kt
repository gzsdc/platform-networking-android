package com.example.baselibs.http.interceptor

import com.example.baselibs.http.exception.ApiException
import okhttp3.Interceptor
import okhttp3.Response


class ErrorHandlingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        // 过滤2xx/3xx其它状态
        if (!response.isSuccessful && !response.isRedirect) {
            throw ApiException(response.code, response.message)
        }

        return response
    }
}