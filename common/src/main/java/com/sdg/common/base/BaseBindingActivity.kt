package com.sdg.ktques.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.dimeno.commons.toolbar.ToolbarActivity
import com.sdg.common.BasePresenter
import com.sdg.common.BaseView
import com.sdg.common.ActivityManager
import com.wangzhen.statusbar.DarkStatusBar
import java.lang.reflect.ParameterizedType


abstract class BaseBindingActivity<VB : ViewBinding,in V : BaseView,P : BasePresenter<V>> : ToolbarActivity(), BaseView {

    lateinit var mContext : Context
    lateinit var mActivity : AppCompatActivity
    var mPresenter : P? = null
    protected val mBinding: VB by lazy {
        //使用反射得到viewbinding的class
        val type = javaClass.genericSuperclass as ParameterizedType
        val aClass = type.actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        method.invoke(null, layoutInflater) as VB
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("base", "onCreate")
        mContext = this
        mActivity = this
        setContentView(mBinding.root)
        ActivityManager.getAppManager().addActivity(this)
        mPresenter = createPresenter()
        if(mPresenter != null) mPresenter!!.attachView(mContext,this as V)

        initViews(savedInstanceState)

        initData()
    }

    abstract fun createPresenter(): P?

    abstract fun initViews(savedInstanceState: Bundle?)

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