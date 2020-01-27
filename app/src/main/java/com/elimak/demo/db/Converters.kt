package com.elimak.demo.db

import androidx.room.TypeConverter
import com.elimak.demo.db.vo.Source
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromSource(value: Source?): String? {
        return value?.let { Gson().toJson(value) }
    }

    @TypeConverter
    fun toSource(json: String?): Source? {
        return json?.let{ Gson().fromJson(json, Source::class.java)}
    }
}