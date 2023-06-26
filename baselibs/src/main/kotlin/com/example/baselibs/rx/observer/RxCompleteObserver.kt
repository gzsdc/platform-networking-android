package com.example.baselibs.rx.observer

import com.example.baselibs.rx.errorhandle.ExceptionHandle
import io.reactivex.CompletableObserver

abstract class RxCompleteObserver : CompletableObserver {

    abstract fun onCompleteSuccess()

    final override fun onComplete() {
        onCompleteSuccess()
        onFinal()
    }

    final override fun onError(throwable: Throwable) {
        ExceptionHandle.handle(
            throwable,
            onNetWorkErrorCallback = {
                onNetWorkError()
            },
            onNormalErrorCallback = {
                onNormalError(it)
            }
        )
        onFinal()
    }

    abstract fun onNormalError(e: Throwable)

    open fun onNetWorkError() = Unit

    open fun onFinal() = Unit
}