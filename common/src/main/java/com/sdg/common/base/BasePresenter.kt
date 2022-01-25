package com.sdg.common

import android.content.Context

interface BasePresenter<in V : BaseView> {
    /**
     * 绑定View
     */
    fun attachView(context: Context,baseView: V)

    /**
     * 解绑View
     */
    fun detachView()

}