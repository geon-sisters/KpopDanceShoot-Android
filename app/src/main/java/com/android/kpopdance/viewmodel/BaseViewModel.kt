package com.android.kpopdance.viewmodel

import android.graphics.Color
import android.view.View
import android.widget.ImageButton
import androidx.lifecycle.*
import com.android.kpopdance.data.Youtube
import com.android.kpopdance.repository.BookmarkRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel(private val bookmarkRepository: BookmarkRepository) : ViewModel() {
    private val _clickedYoutubeId = MutableLiveData<Event<String>>()
    val clickedYoutubeId: LiveData<Event<String>> get() = _clickedYoutubeId

    private val disposables: CompositeDisposable = CompositeDisposable()

    abstract fun onPostBookmarkClicked()

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    fun addToDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun onYoutubeClicked(youtubeId: String) {
        _clickedYoutubeId.value = Event(youtubeId)
    }

    fun onBookmarkClicked(view: View, youtube: Youtube) {
        youtube.isBookmarked = !youtube.isBookmarked
        val imageButton = view as ImageButton
        if (youtube.isBookmarked) {
            bookmarkRepository.insert(youtube.id)
            imageButton.setColorFilter(Color.parseColor("#FFBB33"))
        } else {
            bookmarkRepository.delete(youtube.id)
            imageButton.setColorFilter(Color.parseColor("#7C7C7C"))
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