package com.sdg.toolbar

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.dimeno.commons.toolbar.impl.Toolbar
import com.sdg.common.R

class MenuToolBar(activity : Activity, private var title:String,private var menu : Int,callback : CallBack) : Toolbar(activity) {

    private var ivBack : ImageView? = null
    private var tvTitle : TextView? = null
    private var ivMenu : ImageView? = null
    private var callback : CallBack? = null

    init {
        this.callback = callback
    }

    override fun layoutRes(): Int = R.layout.toolbar_menu

    override fun onViewCreated(view: View) {
        ivBack = view.findViewById<ImageView>(R.id.ivBack).apply {
            setOnClickListener {
                activity.finish()
            }
        }

        tvTitle = view.findViewById<TextView>(R.id.tvTitle).apply{
            text = title
        }

        ivMenu = view.findViewById<ImageView>(R.id.ivMenu).apply {
            setImageResource(menu)
            setOnClickListener {
                callback?.onMenuClick()
            }
        }
    }

    fun setTitle(title:String){
        tvTitle?.text = title
    }

    interface CallBack{
        fun onMenuClick()
    }
}