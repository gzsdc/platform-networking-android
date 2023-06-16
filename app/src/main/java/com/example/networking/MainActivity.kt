package com.example.networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.example.baselibs.rx.observer.RxObserver
import com.example.baselibs.rx.scheduler.IoMainScheduler
import com.example.baselibs.utils.ImageLoader
import com.example.networking.bean.TestData
import com.example.networking.http.MainRetrofit
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        MainRetrofit.service.getHomeBanner()
            .compose(IoMainScheduler())
            .subscribe(object : RxObserver<List<TestData>>() {
                override fun onSuccess(t: List<TestData>) {
                    Log.i("JACK", "onSuccess: " + t )
                    findViewById<ImageView>(R.id.iv_image).let {
                        ImageLoader.loadImage(it, 	"https://static.runoob.com/images/demo/demo2.jpg")
                    }
                }

                override fun onRxError(e: Throwable) {
                    Log.i("JACK", "onRxError: " + e )
                }

            })
    }
}