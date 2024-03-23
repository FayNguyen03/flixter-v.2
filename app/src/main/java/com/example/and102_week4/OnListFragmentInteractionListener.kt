package com.example.and102_week4


import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.RecyclerView

    interface OnListFragmentInteractionListener {
        fun onItemClick(item: TopRated)

        abstract fun RequestParam(): Any
    }

