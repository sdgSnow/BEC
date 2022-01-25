package com.sdg.common.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dimeno.commons.toolbar.ToolbarActivity
import com.sdg.common.ActivityManager
import com.sdg.common.BasePresenter
import com.sdg.common.BaseView
import com.wangzhen.statusbar.DarkStatusBar


abstract class BaseActivity<in V : BaseView,P : BasePresenter<V>> : ToolbarActivity(), BaseView {

    lateinit var mContext : Context
    lateinit var mActivity : AppCompatActivity
    var mPresenter : P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("base", "onCreate")
        mContext = this
        mActivity = this
        ActivityManager.getAppManager().addActivity(this)
        setContentView(getLayout())
        mPresenter = createPresenter()
        if(mPresenter != null) mPresenter!!.attachView(mContext,this as V)

        initViews()

        initData()
    }

    abstract fun getLayout(): Int

    abstract fun createPresenter(): P?

    abstract fun initViews()

    abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        ActivityManager.getAppManager().removeActivityStack(this)
    }

    open fun fitDarkStatusBar(dark: Boolean) {
        val statusBar = DarkStatusBar.get()
        if (dark) statusBar.fitDark(this)
    }

    open fun gotoActivity(clazz: Class<*>){
        gotoActivity(clazz,false,null)
    }

    open fun gotoActivity(clazz: Class<*>,isCloseCurrentActivity: Boolean){
        gotoActivity(clazz,isCloseCurrentActivity,null)
    }

    open fun gotoActivity(clazz: Class<*>,isCloseCurrentActivity:Boolean,bundle: Bundle?){
        var intent = Intent(mContext,clazz)
        if(bundle != null)intent.putExtras(bundle)
        startActivity(intent)
        if(isCloseCurrentActivity){
            mActivity.finish()
        }
    }



}