package com.sdg.toolbar

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.dimeno.commons.toolbar.impl.Toolbar
import com.sdg.common.R

class BackToolBar(mActivity: Activity, private var mTitle:String) : Toolbar(mActivity) {

    private var ivBack : ImageView? = null
    private var tvTitle : TextView? = null

    override fun layoutRes(): Int = R.layout.toolbar_back

    override fun onViewCreated(view: View) {
        ivBack = view.findViewById<ImageView>(R.id.ivBack).apply {
            setOnClickListener {
                activity.finish()
            }
        }

        tvTitle = view.findViewById<TextView>(R.id.tvTitle).apply{
            text = mTitle
        }
    }

    fun setTitle(title:String){
        tvTitle?.text = title
    }
}