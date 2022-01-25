package com.sdg.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.sdg.common.BasePresenter
import com.sdg.common.BaseView
import com.wangzhen.statusbar.DarkStatusBar
import java.lang.reflect.ParameterizedType

abstract class BaseBindingFragment<VB : ViewBinding,in V : BaseView,P : BasePresenter<V>> : Fragment(),BaseView {

    var mPresenter : P? = null
    lateinit var mBinding : VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val type = javaClass.genericSuperclass as ParameterizedType
        val aClass = type.actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java,ViewGroup::class.java,Boolean::class.java)
        mBinding = method.invoke(null,layoutInflater,container,false) as VB
        return mBinding.root
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

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }

    open fun fitDarkStatusBar(dark: Boolean) {
        val statusBar = DarkStatusBar.get()
        if (dark) statusBar.fitDark(activity)
    }
}