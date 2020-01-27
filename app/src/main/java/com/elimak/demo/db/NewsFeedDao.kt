package com.elimak.demo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elimak.demo.db.vo.NewsFeed
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsFeedDao {
    /*** Get a newsFeed by id.
     * @return the newsFeed from the table with a specific id.
     */
    @Query("SELECT * FROM newsfeed_table WHERE id = :id")
    fun getCategoryById(id: Int): Flow<NewsFeed>

    /**
     * Insert a newsFeed in the database. If the newsFeed already exists, replace it.
     * @param newsFeed to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(newsFeed: NewsFeed): Long

    /**
     * Insert all newsFeeds.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<NewsFeed>): List<Long>

    /**
     * Delete all newsFeeds.
     */
    @Query("DELETE FROM newsfeed_table")
    fun deleteAll(): Int

    /*** Get all newsFeeds
     * @return all the newsFeeds from the table
     */
    @Query("SELECT * from newsfeed_table ORDER BY id ASC")
    fun getAll(): List<NewsFeed>
}