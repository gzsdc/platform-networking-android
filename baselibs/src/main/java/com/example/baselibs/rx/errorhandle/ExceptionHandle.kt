package com.example.baselibs.rx.errorhandle


class ExceptionHandle {
    companion object {
        fun handle(throwable: Throwable, onErrorCallback: (throwable: Throwable) -> Unit) {
            //需要处理的异常
            onErrorCallback(throwable)
        }
    }
}