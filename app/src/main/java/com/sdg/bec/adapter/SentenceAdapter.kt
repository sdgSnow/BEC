package com.sdg.bec.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dimeno.adapter.RecyclerAdapter
import com.sdg.bec.adapter.holder.SentenceHolder
import com.sdg.bec.db.Sentence

class SentenceAdapter(list: List<Sentence>) : RecyclerAdapter<Sentence>(list) {
    override fun onAbsCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return SentenceHolder(parent!!)
    }

}