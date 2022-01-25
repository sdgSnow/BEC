package com.sdg.common

import android.content.Context

open class BaseRxPresenter<V : BaseView> : BasePresenter<V>{

    var mView : V? = null
    lateinit var mContext : Context

    override fun attachView(context: Context,baseView: V) {
        this.mView = baseView
        this.mContext = context
    }

    override fun detachView() {
        mView = null
    }

}