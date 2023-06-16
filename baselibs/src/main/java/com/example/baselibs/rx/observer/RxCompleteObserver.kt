package com.example.baselibs.rx.observer

import com.example.baselibs.rx.errorhandle.ExceptionHandle
import io.reactivex.CompletableObserver

abstract class RxCompleteObserver : CompletableObserver {

    abstract fun onCompleteSuccess()

    final override fun onComplete() {
        onCompleteSuccess()
        onFinal()
    }

    final override fun onError(e: Throwable) {
        ExceptionHandle.handle(e) {
            onRxError(it)
        }
        onFinal()
    }

    abstract fun onRxError(exception: Throwable)

    open fun onFinal() = Unit
}