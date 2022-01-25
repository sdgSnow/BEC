package com.sdg.bec.fragment

import android.os.Bundle
import android.view.View
import com.sdg.bec.databinding.FragmentArticleBinding
import com.sdg.bec.presenter.PArticle
import com.sdg.bec.view.VArticle
import com.sdg.common.base.BaseBindingFragment

class ArticleFragment : BaseBindingFragment<FragmentArticleBinding, VArticle, PArticle>(),VArticle{

    companion object {
        fun newInstance(): ArticleFragment? {
            val fragment: ArticleFragment = ArticleFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initData() {

    }

    override fun initViews(view: View, bundle: Bundle?) {

    }

    override fun createPresenter(): PArticle? = PArticle()
}