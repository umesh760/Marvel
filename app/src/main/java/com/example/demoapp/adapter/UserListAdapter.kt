package com.example.demoapp.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoapp.databinding.AdapterlistBinding
import com.example.demoapp.model.ResultsItem

class UserListAdapter(
    private val mList: List<ResultsItem?>?, val mContext: Activity, val from: String
) : RecyclerView.Adapter<UserListAdapter.ListViewHolder>(), Filterable {
    var onItemClick: ((ResultsItem?) -> Unit)? = null
    var filteredList: List<ResultsItem?>? = mList
    lateinit var dataBinding: AdapterlistBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        dataBinding = AdapterlistBinding.inflate(inflater, parent, false)
        return ListViewHolder(dataBinding)
    }

    override fun getItemCount() = filteredList?.size ?: 0

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = filteredList!!.get(position)

        dataBinding.title.text = item!!.name
        dataBinding.date.text = item!!.modified

        Glide.with(mContext)
            .load(item!!.thumbnail?.path + "." + item!!.thumbnail?.extension)
            .into(dataBinding.image)

    }

    inner class ListViewHolder(val itemView: AdapterlistBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(filteredList!!.get(adapterPosition))
            }
        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filteredList = mList
                } else {
                    val resultList = ArrayList<ResultsItem?>()
                    for (row in mList!!) {
                        if (row!!.name?.toLowerCase()
                                ?.contains(constraint.toString().toLowerCase())!!
                        ) {
                            resultList.add(row)
                        }
                    }
                    filteredList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as ArrayList<ResultsItem?>
                notifyDataSetChanged()
            }

        }

    }

}