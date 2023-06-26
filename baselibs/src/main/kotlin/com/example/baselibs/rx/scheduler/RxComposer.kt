package com.example.baselibs.rx.scheduler

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.CompletableTransformer
import io.reactivex.rxjava3.core.MaybeTransformer
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.core.SingleTransformer
import io.reactivex.rxjava3.schedulers.Schedulers



fun <T> applySingleSchedulers() = SingleTransformer<T, T> { upstream ->
    upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun applyCompletableSchedulers() = CompletableTransformer {
    it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> applyObservableSchedulers() = ObservableTransformer<T, T> {
    it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> applyMaybeSchedulers() = MaybeTransformer<T, T> {
    it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}
