package com.elimak.demo.db

interface IDemoDatabase{
    fun newsFeedDao() : NewsFeedDao
}