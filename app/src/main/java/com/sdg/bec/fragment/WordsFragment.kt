package com.sdg.bec.fragment

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.dimeno.network.callback.LoadingCallback
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.sdg.bec.R
import com.sdg.bec.adapter.WordsAdapter
import com.sdg.bec.constant.Path
import com.sdg.bec.databinding.FragmentWordsBinding
import com.sdg.bec.db.Words
import com.sdg.bec.eventbus.AddWordsEvent
import com.sdg.bec.eventbus.SearchWordsEvent
import com.sdg.bec.network.TranslateResult
import com.sdg.bec.network.TranslateTask
import com.sdg.bec.presenter.PWords
import com.sdg.bec.utils.MyUtil
import com.sdg.bec.view.VWords
import com.sdg.bec.widget.WordsHeader
import com.sdg.bec.widget.dialog.AddCallback
import com.sdg.bec.widget.dialog.AddDialog
import com.sdg.common.base.BaseBindingFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.litepal.LitePal
import java.net.URLEncoder

class WordsFragment : BaseBindingFragment<FragmentWordsBinding, VWords, PWords>(),VWords{

    private var mWordsAdapter: WordsAdapter? = null
    private var page:Int = 1

    companion object {
        fun newInstance(): WordsFragment? {
            val fragment: WordsFragment = WordsFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initData() {
        mWordsAdapter = WordsAdapter(arrayListOf())
        mBinding.rcyWords.adapter = mWordsAdapter
        mWordsAdapter?.addHeader(WordsHeader().onCreateView(mBinding.rcyWords))
        mWordsAdapter?.setOnClickCallback { _, position ->
            val cnName = mWordsAdapter!!.datas[position].cnName
            val enName = mWordsAdapter!!.datas[position].enName
            val encode = URLEncoder.encode(enName, "utf-8")
            val sign = MyUtil.getSign(enName)
//            if(mWordsAdapter!!.datas[position].status == Constant.Status.memorize){
//                mWordsAdapter!!.datas[position].status = Constant.Status.add
//            }else {
//                mWordsAdapter!!.datas[position].status++
//            }
//            mWordsAdapter?.notifyDataSetChanged()
//            var words = Words()
//            words.status = mWordsAdapter!!.datas[position].status
//            val saveOrUpdate = words.saveOrUpdate("wID = ?", mWordsAdapter!!.datas[position].getwID())
//            Log.i("bec", "更新状态 ->$saveOrUpdate")

            //百度翻译
            TranslateTask(object : LoadingCallback<TranslateResult>(){
                override fun onSuccess(data: TranslateResult?) {
                    //结果拼接
                    var result = ""
                    if(data?.error_code == 52000) {
                        for (transResultBean in data.trans_result!!) {
                            result += transResultBean.src
                        }
                        ToastUtils.showLong(result)
                    }else{
                        ToastUtils.showLong("请求异常码" + data?.error_code)
                    }
                }

                override fun onError(code: Int, message: String?) {
                    super.onError(code, message)
                }
            }).setTag(this)
                .put("q",encode)
                .put("from","en")
                .put("to","zh")
                .put("appid",Path.APPID)
                .put("salt",Path.salt)
                .put("sign",sign)
                .exe()
        }
        page = 1
        mPresenter?.initWordsData(page)
    }

    override fun initViews(view: View, bundle: Bundle?) {
        EventBus.getDefault().register(this)
        //设置上下拉刷新的配置

        //设置上下拉刷新的配置
        mBinding.refreshLayout.setPrimaryColorsId(R.color.f5f4, R.color.app_back2)
        mBinding.refreshLayout.setBackgroundColor(resources.getColor(R.color.f5f4))
        mBinding.refreshLayout.setRefreshHeader(ClassicsHeader(context))
        mBinding.refreshLayout.setRefreshFooter(ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Scale))
        mBinding.rcyWords.layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)

        mBinding.addWord.setOnClickListener {
            AddDialog().setTitle("新增单词").setCancle(true).setCancleTouch(false).setCallback(object : AddCallback{

                override fun add(cnName: String, enName: String) {
                    MyUtil.addWord(cnName,enName)
                }

                override fun no() {

                }

            }).show(activity?.supportFragmentManager!!)
        }

        //下拉刷新
        mBinding.refreshLayout.setOnRefreshListener {
            //恢复没有更多数据的原始状态 1.0.5
            mBinding.refreshLayout.setNoMoreData(false)
            page = 1
            mPresenter?.initWordsData(page)
        }
        //上拉加载
        mBinding.refreshLayout.setOnLoadMoreListener {
            mPresenter?.initWordsData(page)
            page++
        }

        //权限申请


    }

    override fun createPresenter(): PWords? = PWords()

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    fun searchResult(event: SearchWordsEvent) {
        mWordsAdapter?.setData(event.list)
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    fun addWord(event: AddWordsEvent) {
        val wordsList = LitePal.select().find(Words::class.java)
        mWordsAdapter?.setData(wordsList)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun onSuccess(loadWords: List<Words>) {
        mWordsAdapter?.addData(loadWords)
        refreshLayoutStatusDeal()
        if(loadWords.size < 20) {
            mBinding.refreshLayout.finishLoadMoreWithNoMoreData()
        }
    }

    override fun onError() {
        refreshLayoutStatusDeal()
    }

    private fun refreshLayoutStatusDeal(){
        if (mBinding.refreshLayout.state == RefreshState.Refreshing) {
            mBinding.refreshLayout.finishRefresh(true)
        }
        if (mBinding.refreshLayout.state == RefreshState.Loading) {
            mBinding.refreshLayout.finishLoadMore(true)
        }
    }

}