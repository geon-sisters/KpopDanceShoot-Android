package com.android.kpopdance

import com.android.kpopdance.model.AppDatabase
import com.android.kpopdance.model.YoutubeApi
import com.android.kpopdance.repository.BookmarkRepository
import com.android.kpopdance.repository.YoutubeRepository
import com.android.kpopdance.viewmodel.HomeViewModel
import com.android.kpopdance.viewmodel.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule: Module = module {
    single { AppDatabase.getInstance(androidContext()) }

    single { get<AppDatabase>().getBookmarkDao() }

    single { BookmarkRepository(get()) }

    single {
        Retrofit.Builder()
            .baseUrl("https://rpltb29g31.execute-api.us-east-2.amazonaws.com/2021-01-22/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(YoutubeApi::class.java)
    }

    single { YoutubeRepository(get(), get()) }

    viewModel { HomeViewModel(get(), get()) }

    viewModel { SearchViewModel(get(), get()) }
}