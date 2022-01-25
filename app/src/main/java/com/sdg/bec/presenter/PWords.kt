package com.sdg.bec.presenter

import com.sdg.bec.db.Words
import com.sdg.bec.view.VMain
import com.sdg.bec.view.VWords
import com.sdg.common.BaseRxPresenter
import com.socks.library.KLog
import org.litepal.LitePal
import java.util.ArrayList

class PWords : BaseRxPresenter<VWords>() {

//    private var curWords: ArrayList<Words> = ArrayList<Words>()
    //curWords: ArrayList<Words>
    fun initWordsData(page:Int){
        var size = 20
        val queryWords = LitePal.select().limit(20).offset(page * size).find(Words::class.java)
        if(queryWords.size > 0){
            mView?.onSuccess(queryWords)
        }else{
            mView?.onError()
        }
    }

}