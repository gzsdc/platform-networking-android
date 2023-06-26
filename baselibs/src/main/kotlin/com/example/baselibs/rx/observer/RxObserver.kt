package com.example.baselibs.rx.observer

import com.example.baselibs.rx.errorhandle.ExceptionHandle
import io.reactivex.rxjava3.core.Observer

abstract class RxObserver<T> : Observer<T> {

    abstract fun onSuccess(t: T)

    override fun onSubscribe(d: io.reactivex.rxjava3.disposables.Disposable) = Unit

    final override fun onNext(t: T) {
        onSuccess(t)
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

    final override fun onComplete() = Unit

    open fun onFinal() = Unit
}