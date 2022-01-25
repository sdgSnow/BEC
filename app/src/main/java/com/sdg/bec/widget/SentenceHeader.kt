package com.sdg.bec.widget

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.dimeno.adapter.base.RecyclerItem
import com.sdg.bec.R
import com.sdg.bec.db.Sentence
import com.sdg.bec.eventbus.SearchSentenceEvent
import org.greenrobot.eventbus.EventBus
import org.litepal.LitePal

class SentenceHeader() : RecyclerItem() {

    override fun layout(): Int = R.layout.header_sentence

    override fun onViewCreated(itemView: View) {
        var search = itemView.findViewById<EditText>(R.id.search)
        search.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                search(s.toString().trim())
            }
        })
    }

    fun search(content: String){
        var result = LitePal.select()
            .where("sort = ? or cnName like ? or enName like ? or status = ?", content, "%$content%", "%$content%", content)
            .find(Sentence::class.java)
        EventBus.getDefault().postSticky(SearchSentenceEvent(result))
    }

}