package com.example.demoapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.demoapp.databinding.ActivityDetailsBinding
import com.example.demoapp.model.ResultsItem

class DetailsActivity : AppCompatActivity() {

    lateinit var itemData: ResultsItem
    lateinit var dataBinding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = dataBinding.root
        setContentView(view)

        itemData = intent.getSerializableExtra("itemData") as ResultsItem

        dataBinding.txtTitle.text = itemData.name
        dataBinding.txtDescription.text = itemData.description

        Glide.with(this@DetailsActivity)
            .load(itemData.thumbnail?.path + "." + itemData.thumbnail?.extension)
            .into(dataBinding.imgFull)
    }
}