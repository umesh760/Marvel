package com.example.demoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demoapp.model.ListResponse
import com.example.demoapp.model.ResultsItem
import com.example.demoapp.repository.Reposatory
import java.util.*

class DataVM(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    var mList = ArrayList<ResultsItem>()
    lateinit var commonResponse: MutableLiveData<ListResponse>
    val reposatory: Reposatory

    init {
        reposatory = Reposatory.getInstance()!!
    }

    fun getlistData(
        apikey: String, hash: String, ts: String
    ): LiveData<ListResponse> {
        commonResponse = reposatory!!.getList(apikey, hash, ts)
        return commonResponse!!
    }

}