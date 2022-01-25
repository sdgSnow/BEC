package com.sdg.bec.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dimeno.adapter.RecyclerAdapter
import com.sdg.bec.adapter.holder.WordsHolder
import com.sdg.bec.db.Words

class WordsAdapter(list: ArrayList<Words>) : RecyclerAdapter<Words>(list) {
    override fun onAbsCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return WordsHolder(parent!!)
    }

}