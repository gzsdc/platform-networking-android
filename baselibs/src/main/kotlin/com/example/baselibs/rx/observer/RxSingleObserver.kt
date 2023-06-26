package com.example.baselibs.rx.observer

import com.example.baselibs.rx.errorhandle.ExceptionHandle
import io.reactivex.SingleObserver

abstract class RxSingleObserver<T> : SingleObserver<T> {

    abstract fun onSingleSuccess(t: T)

    final override fun onSuccess(t: T) {
        onSingleSuccess(t)
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