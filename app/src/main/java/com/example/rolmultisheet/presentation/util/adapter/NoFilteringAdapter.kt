package com.example.rolmultisheet.presentation.util.adapter

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import com.example.rolmultisheet.R

class NoFilteringAdapter<T>(context: Context, val items: List<T>) :
    ArrayAdapter<T>(context, R.layout.list_item, items) {

    private val noOpFilter = object : Filter() {
        private val noOpResult = FilterResults()
        override fun performFiltering(constraint: CharSequence?) = noOpResult
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {}
    }

    override fun getFilter() = noOpFilter
}