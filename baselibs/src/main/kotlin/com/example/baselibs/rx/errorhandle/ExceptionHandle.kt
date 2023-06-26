package com.example.baselibs.rx.errorhandle

import android.accounts.NetworkErrorException


class ExceptionHandle {
    companion object {
        fun handle(throwable: Throwable, onNetWorkErrorCallback: () -> Unit, onNormalErrorCallback: (throwable: Throwable) -> Unit) {
            if (throwable is NetworkErrorException) {
                onNetWorkErrorCallback()
            } else {
                onNormalErrorCallback(throwable)
            }
        }
    }
}