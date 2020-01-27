package com.elimak.demo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.elimak.demo.db.vo.NewsFeed

@Database(entities = [NewsFeed::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DemoDatabase : RoomDatabase(), IDemoDatabase {

    abstract override fun newsFeedDao() : NewsFeedDao

    companion object {
        @Volatile
        private var INSTANCE: DemoDatabase? = null

        fun getDatabase(context: Context): IDemoDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(ctx: Context) =
            Room.databaseBuilder(ctx.applicationContext, DemoDatabase::class.java, "demo_database")
                .fallbackToDestructiveMigration()
                .build()
    }
}

