package com.example.demoapp.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ListResponse(

    @SerializedName("copyright")
    var copyright: String? = null,

    @SerializedName("code")
    var code: Int? = null,

    @SerializedName("data")
    var data: Data? = null,

    @SerializedName("attributionHTML")
    var attributionHTML: String? = null,

    @SerializedName("attributionText")
    var attributionText: String? = null,

    @SerializedName("etag")
    var etag: String? = null,

    @SerializedName("status")
    var status: String? = null
) : Serializable

data class UrlsItem(

    @SerializedName("type")
    var type: String? = null,

    @SerializedName("url")
    var url: String? = null
) : Serializable

data class Thumbnail(

    @SerializedName("path")
    var path: String? = null,

    @SerializedName("extension")
    var extension: String? = null
) : Serializable

data class Comics(

    @SerializedName("collectionURI")
    var collectionURI: String? = null,

    @SerializedName("available")
    var available: Int? = null,

    @SerializedName("returned")
    var returned: Int? = null,

    @SerializedName("items")
    var items: List<ItemsItem?>? = null
) : Serializable

@Entity(tableName = "ResultsItem")
data class ResultsItem(

    @PrimaryKey(autoGenerate = true)
    var uid: Int? = null,

    @SerializedName("thumbnail")
    var thumbnail: Thumbnail? = null,

    @SerializedName("urls")
    @Ignore
    var urls: List<UrlsItem?>? = null,

    @SerializedName("stories")
    @Ignore
    var stories: Stories? = null,

    @SerializedName("series")
    @Ignore
    var series: Series? = null,

    @SerializedName("comics")
    @Ignore
    var comics: Comics? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("modified")
    var modified: String? = null,

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("resourceURI")
    var resourceURI: String? = null,

    @SerializedName("events")
    @Ignore
    var events: Events? = null
) : Serializable

data class Stories(

    @SerializedName("collectionURI")
    var collectionURI: String? = null,

    @SerializedName("available")
    var available: Int? = null,

    @SerializedName("returned")
    var returned: Int? = null,

    @SerializedName("items")
    var items: List<ItemsItem?>? = null
) : Serializable

data class Series(

    @SerializedName("collectionURI")
    var collectionURI: String? = null,

    @SerializedName("available")
    var available: Int? = null,

    @SerializedName("returned")
    var returned: Int? = null,

    @SerializedName("items")
    var items: List<ItemsItem?>? = null
) : Serializable

data class ItemsItem(

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("resourceURI")
    var resourceURI: String? = null,

    @SerializedName("type")
    var type: String? = null
) : Serializable

data class Events(

    @SerializedName("collectionURI")
    var collectionURI: String? = null,

    @SerializedName("available")
    var available: Int? = null,

    @SerializedName("returned")
    var returned: Int? = null,

    @SerializedName("items")
    var items: List<ItemsItem?>? = null
) : Serializable

data class Data(

    @SerializedName("total")
    var total: Int? = null,

    @SerializedName("offset")
    var offset: Int? = null,

    @SerializedName("limit")
    var limit: Int? = null,

    @SerializedName("count")
    var count: Int? = null,

    @SerializedName("results")
    var results: List<ResultsItem?>? = null
) : Serializable
