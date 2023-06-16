package com.example.baselibs.rx.observer

import com.example.baselibs.rx.errorhandle.ExceptionHandle
import io.reactivex.SingleObserver

abstract class RxSingleObserver<T> : SingleObserver<T> {

    abstract fun onSingleSuccess(t: T)

    final override fun onSuccess(t: T) {
        onSingleSuccess(t)
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