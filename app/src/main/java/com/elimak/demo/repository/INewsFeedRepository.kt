package com.elimak.demo.repository

import com.elimak.demo.core.data.IRepository
import com.elimak.demo.db.vo.NewsFeed

interface INewsFeedRepository: IRepository {
    suspend fun loadArticles(force: Boolean): List<NewsFeed>
}