package com.android.kpopdance.viewmodel

import androidx.lifecycle.*
import com.android.kpopdance.data.Youtube
import com.android.kpopdance.repository.BookmarkRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel(private val bookmarkRepository: BookmarkRepository) : ViewModel() {
    private val _clickedYoutube = MutableLiveData<Event<Youtube>>()
    val clickedYoutube: LiveData<Event<Youtube>> get() = _clickedYoutube

    private val disposables: CompositeDisposable = CompositeDisposable()

    abstract fun onPostBookmarkClicked()

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    fun addToDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun onYoutubeClicked(id: String, title: String) {
        _clickedYoutube.value = Event(Youtube(title = title, id = id))
    }

    fun onBookmarkClicked(youtube: Youtube) {
        youtube.isBookmarked = !youtube.isBookmarked
        if (youtube.isBookmarked) {
            bookmarkRepository.insert(youtube.id)
        } else {
            bookmarkRepository.delete(youtube.id)
        }
        onPostBookmarkClicked()
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