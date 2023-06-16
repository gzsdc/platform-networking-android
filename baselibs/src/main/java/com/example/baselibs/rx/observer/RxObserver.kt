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

    final override fun onError(e: Throwable) {
        ExceptionHandle.handle(e) {
            onRxError(it)
        }
        onFinal()
    }

    abstract fun onRxError(e: Throwable)

    final override fun onComplete() = Unit

    open fun onFinal() = Unit
}