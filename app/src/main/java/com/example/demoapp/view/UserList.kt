package com.example.demoapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.demoapp.databinding.ContentMainBinding
import com.example.demoapp.model.ResultsItem
import com.example.demoapp.adapter.UserListAdapter
import com.example.demoapp.database.roomDatabase
import com.example.demoapp.viewmodel.DataVM
import android.net.ConnectivityManager


class UserList : AppCompatActivity() {

    lateinit var model: DataVM
    lateinit var adapter: UserListAdapter
    lateinit var dataBinding: ContentMainBinding
    lateinit var db: roomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = ContentMainBinding.inflate(layoutInflater)
        val view = dataBinding.root
        setContentView(view)

        model = ViewModelProviders.of(this@UserList).get(DataVM::class.java)

        db = roomDatabase.getDatabase(this@UserList)!!
        getApplicantList()

        dataBinding.userSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                Log.d("onQueryTextChange", "query: " + query)
                adapter?.filter?.filter(query)
                return true
            }
        })
    }


    private fun setupAdapter(mList: List<ResultsItem?>?) {
        if (mList != null) {
            adapter = UserListAdapter(mList, this@UserList, "Listdata")
            val layoutManager = LinearLayoutManager(this)
            dataBinding.listview.layoutManager = layoutManager
            dataBinding.listview.addItemDecoration(
                DividerItemDecoration(
                    this,
                    layoutManager.orientation
                )
            )
            dataBinding.listview.adapter = adapter
            adapter.onItemClick = { item ->

                val intent = Intent(this@UserList, DetailsActivity::class.java)
                intent.putExtra("id", item?.id)
                intent.putExtra("itemData", item)
                startActivity(intent)
            }
        }
    }

    fun getApplicantList() {

        if (isNetworkConnected()) {

            model.getlistData(
                "07370ea626cd80f3f7fcaf1a958bfffb",
                "9949b5f691ecd05650fdc887c009b475",
                "123456"
            ).observe(this, {
                try {
                    it.data
                    Log.e("dataRes ", "dataRes" + it.data?.results!!.size)

                    if (it.data?.results?.size!! > 0) {
                        setupAdapter(it.data?.results as ArrayList<ResultsItem?>?)
                        db.ResultsItemDao().deleteAllData()

                        db.ResultsItemDao().insertAllData(it.data?.results)

                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e("dataError ", "Exception")
                }
            })

        } else {
            val itemList: List<ResultsItem?>? = db.ResultsItemDao().getAllData()
            setupAdapter(itemList)

        }

    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}