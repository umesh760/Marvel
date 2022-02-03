package com.example.demoapp.repository

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.demoapp.model.ListResponse
import com.example.demoapp.retrofit.restClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Reposatory {

    companion object CompanionObj {
        var bhuReposatory: Reposatory? = null
        fun getInstance(): Reposatory? {
            if (bhuReposatory == null) {
                bhuReposatory = Reposatory()
            }
            return bhuReposatory
        }
    }


    fun getList(apikey: String, hash: String, ts: String): MutableLiveData<ListResponse> {
        var newsData: MutableLiveData<ListResponse> = MutableLiveData<ListResponse>()
        val restClient = restClient(null).getWebService()?.getList(apikey, hash, ts)

        restClient?.enqueue(object : Callback<ListResponse> {
            override fun onResponse(call: Call<ListResponse>?, response: Response<ListResponse>?) {
                if (response != null) {
                    if (response.body() != null) {
                        newsData.value = response.body()
                    }
                }
            }

            @SuppressLint("NullSafeMutableLiveData")
            override fun onFailure(call: Call<ListResponse>?, t: Throwable?) {
                newsData.value = null
            }

        })
        return newsData
    }


    fun getListData(apikey: String, hash: String, ts: String): MutableLiveData<ListResponse> {
        var newsData: MutableLiveData<ListResponse> = MutableLiveData<ListResponse>()
        val restClient = restClient(null).getWebService()?.getListData(apikey, hash, ts)

        restClient?.enqueue(object : Callback<ListResponse> {
            override fun onResponse(call: Call<ListResponse>?, response: Response<ListResponse>?) {
                if (response != null) {
                    if (response.body() != null) {
                        newsData.value = response.body()
                    }
                }
            }

            @SuppressLint("NullSafeMutableLiveData")
            override fun onFailure(call: Call<ListResponse>?, t: Throwable?) {
                newsData.value = null
            }

        })
        return newsData
    }

}