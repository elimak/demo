package com.elimak.demo.di

import android.app.Application
import android.content.Context
import com.elimak.demo.App
import com.elimak.demo.api.IApiServices
import com.elimak.demo.db.DemoDatabase
import com.elimak.demo.db.IDemoDatabase
import com.elimak.demo.repository.INewsFeedRepository
import com.elimak.demo.repository.NewsFeedRepository

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
class ApplicationModule(private val app: App) {

    @Provides
    @AppContext
    fun provideContext(): Context = app.applicationContext

    @Provides
    @AppContext
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    protected fun provideRepository(): INewsFeedRepository {
        return NewsFeedRepository(app)
    }

    @Provides
    @Singleton
    protected fun api(): IApiServices {
        return IApiServices.create(app)
    }

    @Provides
    @Singleton
    protected fun database(): IDemoDatabase {
        return DemoDatabase.getDatabase(app)
    }
}