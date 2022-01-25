package com.sdg.bec.adapter.holder

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dimeno.adapter.base.RecyclerViewHolder
import com.sdg.bec.R
import com.sdg.bec.adapter.WordsAdapter
import com.sdg.bec.constant.Constant
import com.sdg.bec.db.Sentence

class SentenceHolder(val parent: ViewGroup) : RecyclerViewHolder<Sentence>(parent, R.layout.item_sentence) {
    override fun bind() {
        var headersCount = 0
        if(parent is RecyclerView){
            if((parent as RecyclerView).adapter is WordsAdapter){
                val wordsAdapter = (parent as RecyclerView).adapter as WordsAdapter
                headersCount = wordsAdapter.headersCount
            }
        }
        findViewById<TextView>(R.id.sort).apply {
            text = (adapterPosition - headersCount + 1).toString()
        }

        findViewById<TextView>(R.id.enName).apply {
            text = mData.enName
        }

        findViewById<TextView>(R.id.cnName).apply {
            text = mData.cnName
        }
        findViewById<View>(R.id.vStatus).apply {
            when(mData.status){
                Constant.Status.add -> setBackgroundColor(resources.getColor(R.color.add))
                Constant.Status.know -> setBackgroundColor(resources.getColor(R.color.know))
                Constant.Status.memorize -> setBackgroundColor(resources.getColor(R.color.memorize))
                else -> setBackgroundColor(resources.getColor(R.color.add))
            }

        }
    }
}