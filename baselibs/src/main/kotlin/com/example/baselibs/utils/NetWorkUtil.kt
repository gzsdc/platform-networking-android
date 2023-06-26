package com.example.baselibs.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.baselibs.config.AppConfig

object NetWorkUtil {

    /**
     * 判断网络是否连接
     *
     * 需添加权限
     * `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>`
     */
    fun isConnected(): Boolean {
        return isConnected(AppConfig.getApplication())
    }

    private fun isConnected(context: Context): Boolean {
        val info = getActiveNetworkInfo(context)
        return info != null && info.isConnected
    }

    @SuppressLint("MissingPermission")
    private fun getActiveNetworkInfo(context: Context): NetworkInfo? {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }
}