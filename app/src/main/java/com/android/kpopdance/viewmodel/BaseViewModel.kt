package com.android.kpopdance.viewmodel

import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {
    private val _clickedYoutubeId = MutableLiveData<Event<String>>()
    val clickedYoutubeId: LiveData<Event<String>> get() = _clickedYoutubeId

    private val disposables: CompositeDisposable = CompositeDisposable()

    fun addToDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    fun onYoutubeClicked(youtubeId: String) {
        _clickedYoutubeId.value = Event(youtubeId)
    }
}

inline fun <T> LiveData<Event<T>>.eventObserve(
    owner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
): Observer<Event<T>> {
    val wrappedObserver = Observer<Event<T>> { t ->
        t.getContentIfNotHandled()?.let {
            onChanged.invoke(it)
        }
    }
    observe(owner, wrappedObserver)
    return wrappedObserver
}