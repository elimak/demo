package com.elimak.demo.di

import android.content.Context
import com.elimak.demo.App
import com.elimak.demo.repository.NewsFeedRepository
import com.elimak.demo.ui.main.MainViewModel

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    // APP
    fun inject(application: App)

    // VIEW MODELS
    fun inject(viewModel: MainViewModel)

    // REPOSITORY
    fun inject(newsFeedRepository: NewsFeedRepository)

    @AppContext
    fun getContext(): Context
}