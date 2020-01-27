package com.elimak.demo.ui.main

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import com.elimak.demo.App
import com.elimak.demo.repository.NewsFeedRepository
import com.elimak.demo.core.ui.ViewModelBase
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@UseExperimental(ExperimentalCoroutinesApi::class)
class MainViewModel(application: Application) : ViewModelBase(application) {

    @Inject lateinit var newsFeedRepository: NewsFeedRepository

    val text: ObservableField<String> = ObservableField("Main Fragment Title")

    init {
        App.injector.inject(this)
        start()
    }

    fun start() {
        viewModelScope.launch {
            try {
                val result = newsFeedRepository.loadArticles(true)
                Log.d("val", Gson().toJson(result))
            } catch (e: Exception) {
                Log.d("val", e.message)
            }

        }
    }
}
