package com.wayyan.countriessqldelight.base.helper

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

class AsyncViewStateLiveData<T> : LiveData<AsyncViewState<T>>() {

    override fun observe(
        owner: LifecycleOwner,
        observer: Observer<in AsyncViewState<T>>
    ) {
        if (hasActiveObservers()) {
            Log.d("AsyncViewStateLiveData",
                "Multiple observers registered but only one will be notified of changes."
            )
        }
        super.observe(owner, observer)
    }

    fun postLoading() {
        this.postValue(AsyncViewState.Loading())
    }

    fun postSuccess(data: T) {
        this.postValue(AsyncViewState.Success(data))
    }

    fun postError(
        exception: Throwable,
        error: String
    ) {
        this.postValue(AsyncViewState.Error(exception, error))
    }

}