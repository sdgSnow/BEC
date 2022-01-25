package com.sdg.bec.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.sdg.bec.adapter.SentenceAdapter
import com.sdg.bec.constant.Constant
import com.sdg.bec.databinding.FragmentSentenceBinding
import com.sdg.bec.db.Sentence
import com.sdg.bec.eventbus.SearchSentenceEvent
import com.sdg.bec.presenter.PSentence
import com.sdg.bec.view.VSentence
import com.sdg.bec.widget.SentenceHeader
import com.sdg.common.base.BaseBindingFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.litepal.LitePal

class SentenceFragment : BaseBindingFragment<FragmentSentenceBinding, VSentence, PSentence>(),VSentence{

    private var mSentenceAdapter:SentenceAdapter? = null

    companion object {
        fun newInstance(): SentenceFragment? {
            val fragment: SentenceFragment = SentenceFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initData() {
        val sentenceList = LitePal.select().find(Sentence::class.java)
        for (index in 0 until sentenceList.size) {
            sentenceList[index].sort = index + 1
        }
        mSentenceAdapter = SentenceAdapter(sentenceList)
        mBinding.rcySentence.adapter = mSentenceAdapter
        mSentenceAdapter?.addHeader(SentenceHeader().onCreateView(mBinding.rcySentence))
        mSentenceAdapter?.setOnClickCallback { _, position ->
            if(sentenceList[position].status == Constant.Status.memorize){
                sentenceList[position].status = Constant.Status.add
            }else {
                sentenceList[position].status++
            }
            mSentenceAdapter?.notifyItemChanged(position)
            var sentence = Sentence()
            sentence.status = sentenceList[position].status
            val saveOrUpdate = sentence.saveOrUpdate("sID = ?", sentenceList[position].getsID())
            Log.i("bec", "更新状态 ->$saveOrUpdate")
        }
    }

    override fun initViews(view: View, bundle: Bundle?) {
        EventBus.getDefault().register(this)
        mBinding.rcySentence.layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)
    }

    override fun createPresenter(): PSentence? = PSentence()

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    fun searchResult(event: SearchSentenceEvent) {
        for (index in event.list.indices) {
            event.list[index].sort = index + 1
        }
        mSentenceAdapter?.setData(event.list)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}