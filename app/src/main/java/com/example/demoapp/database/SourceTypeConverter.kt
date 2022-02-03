package com.example.demoapp.database

import androidx.room.TypeConverter
import com.example.demoapp.model.Thumbnail
import org.json.JSONObject


class SourceTypeConverter {

    @TypeConverter
    fun fromThumbnail(thumbnail: Thumbnail): String {
        return JSONObject().apply {
            put("path", thumbnail.path)
            put("extension", thumbnail.extension)
        }.toString()
    }

    @TypeConverter
    fun toThumbnail(thumbnail: String): Thumbnail {
        val json = JSONObject(thumbnail)
        return Thumbnail(json.getString("path"), json.getString("extension"))
    }
}