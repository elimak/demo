package com.elimak.demo.repository

import android.content.Context
import com.elimak.demo.App
import com.elimak.demo.api.IApiServices
import com.elimak.demo.core.data.RepositoryBase
import com.elimak.demo.db.IDemoDatabase
import com.elimak.demo.db.vo.NewsFeed
import com.elimak.demo.di.AppContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ExperimentalCoroutinesApi
class NewsFeedRepository
    @Inject constructor(@AppContext private val appContext: Context) : RepositoryBase(), INewsFeedRepository {

    @Inject lateinit var api: IApiServices
    @Inject lateinit var database: IDemoDatabase

    init {
        App.injector.inject(this)
    }

    override suspend fun loadArticles(force: Boolean): List<NewsFeed> {
        return withContext(Dispatchers.IO) {

            // check if we have the data in the DB, if we do we wont load it again unless the "force" arg is true
            // we could imagine refreshing this data every n days and thats where we would use "force"
            var list: List<NewsFeed> = database.newsFeedDao().getAll()

            // if we don't have entries in the DB or if we want to refresh the data with the online source
            if(list.isEmpty() || force) {
                val response = api.getTopArticles("us")
                if(response.status.equals("ok")) {
                    if(response.articles.isNotEmpty()) {
                        // insert / update new data, return data inserted
                        database.newsFeedDao().insertAll(response.articles)
                        list = database.newsFeedDao().getAll()
                    }
                }

            }
            list
        }
    }
}