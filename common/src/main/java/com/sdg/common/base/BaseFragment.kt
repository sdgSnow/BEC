package com.sdg.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wangzhen.statusbar.DarkStatusBar

abstract class BaseFragment<in V : BaseView,P : BasePresenter<V>> : Fragment(),BaseView {

    var mPresenter : P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(getLayout(),container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter = createPresenter()
        if(mPresenter != null) mPresenter!!.attachView(context!!,this as V)

        initViews(view,savedInstanceState)

        initData()
    }

    abstract fun initData()

    abstract fun initViews(view: View,bundle: Bundle?)

    abstract fun createPresenter(): P?

    abstract fun getLayout(): Int

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }

    open fun fitDarkStatusBar(dark: Boolean) {
        val statusBar = DarkStatusBar.get()
        if (dark) statusBar.fitDark(activity)
    }
}