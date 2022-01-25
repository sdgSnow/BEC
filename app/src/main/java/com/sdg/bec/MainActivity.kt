package com.sdg.bec

import android.os.Bundle
import android.view.View
import com.dimeno.commons.toolbar.impl.Toolbar
import com.sdg.bec.databinding.ActivityMainBinding
import com.sdg.bec.fragment.ArticleFragment
import com.sdg.bec.fragment.MineFragment
import com.sdg.bec.fragment.SentenceFragment
import com.sdg.bec.fragment.WordsFragment
import com.sdg.bec.presenter.PMain
import com.sdg.bec.view.VMain
import com.sdg.bec.widget.bar.BottomBar
import com.sdg.ktques.base.BaseBindingActivity
import com.sdg.toolbar.TitleToolBar
import org.greenrobot.eventbus.EventBus

class MainActivity : BaseBindingActivity<ActivityMainBinding,VMain,PMain>(),VMain , BottomBar.OnItemListener {
    private var wordsFragment: WordsFragment? = null
    private var sentenceFragment: SentenceFragment? = null
    private var mineFragment: MineFragment? = null
    private var articleFragment: ArticleFragment? = null

    override fun createPresenter(): PMain? = PMain()

    override fun createToolbar(): Toolbar? = TitleToolBar(this,"bec")

    override fun initViews(savedInstanceState: Bundle?) {
        //根据savedInstanceState是否等于null判断activity是新建还是销毁重建
        //根据savedInstanceState是否等于null判断activity是新建还是销毁重建
        if (savedInstanceState == null) {
            //activity第一次创建的时候，savedInstanceState=null，创建WordsFragment
            wordsFragment = WordsFragment.newInstance()
            sentenceFragment = SentenceFragment.newInstance()
            mineFragment = MineFragment.newInstance()
            articleFragment = ArticleFragment.newInstance()
        } else {
            //恢复状态
            wordsFragment = supportFragmentManager.getFragment(savedInstanceState, "wordsFragment") as WordsFragment?
            if (wordsFragment == null) {
                wordsFragment = WordsFragment.newInstance()
            }
            sentenceFragment = supportFragmentManager.getFragment(savedInstanceState, "sentenceFragment") as SentenceFragment?
            if (sentenceFragment == null) {
                sentenceFragment = SentenceFragment.newInstance()
            }
            mineFragment = supportFragmentManager.getFragment(savedInstanceState, "mineFragment") as MineFragment?
            if (mineFragment == null) {
                mineFragment = MineFragment.newInstance()
            }
            articleFragment = supportFragmentManager.getFragment(savedInstanceState, "articleFragment") as ArticleFragment?
            if (articleFragment == null) {
                articleFragment = ArticleFragment.newInstance()
            }
        }

        initBar()
    }

    override fun initData() {

    }

    fun initBar() {
        mBinding.bottomBar.init(supportFragmentManager, R.id.fl_main)
            .addItem("BEC单词", R.mipmap.info, R.mipmap.info, wordsFragment, false)
            .addItem("重点语句", R.mipmap.info, R.mipmap.info, sentenceFragment, false)
            .addItem("精美文章", R.mipmap.info, R.mipmap.info, articleFragment, false)
            .addItem("我的", R.mipmap.info, R.mipmap.info, mineFragment, false)
            .defaultIndext(0)
        mBinding.bottomBar.setOnItemListener(this)
    }

    override fun onItem(i: Int) {

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //保存frament
        //保存frament
        if (wordsFragment != null) {
            supportFragmentManager.putFragment(outState, "wordsFragment", sentenceFragment!!)
        }
        if (sentenceFragment != null) {
            supportFragmentManager.putFragment(outState, "sentenceFragment", sentenceFragment!!)
        }
        if (mineFragment != null) {
            supportFragmentManager.putFragment(outState, "mineFragment", mineFragment!!)
        }
        if (articleFragment != null) {
            supportFragmentManager.putFragment(outState, "articleFragment", articleFragment!!)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}