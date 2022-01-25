package com.sdg.toolbar

import android.app.Activity
import android.view.View
import android.widget.TextView
import com.dimeno.commons.toolbar.impl.Toolbar
import com.sdg.common.R

class TitleToolBar(activity: Activity, private var title:String) : Toolbar(activity) {

    private var tvTitle : TextView? = null

    override fun layoutRes(): Int = R.layout.toolbar_title

    override fun onViewCreated(view: View) {
        tvTitle = view.findViewById<TextView>(R.id.tvTitle).apply{
            text = title
        }
    }

    fun setTitle(title:String){
        tvTitle?.text = title
    }
}