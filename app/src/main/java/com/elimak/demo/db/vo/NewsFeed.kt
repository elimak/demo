package com.elimak.demo.db.vo

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson

@Entity(tableName = "newsfeed_table")
class NewsFeed constructor(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                           val name: String?="",
                           var source: Source,
                           val author: String?="",
                           val title: String?="",
                           val description: String?="",
                           val content: String?="",
                           val urlToImage: String?="",
                           val publishedAt: String?="",
                           val url: String?=""){



    override fun toString(): String {
        return Gson().toJson(this)
    }
}