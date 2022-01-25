package com.sdg.bec.widget.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.sdg.bec.R

abstract class BaseDialogFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout(),container,false)
    }

    /**
     * 布局
     * */
    abstract fun layout() : Int

    /**
     * 设置布局宽
     * */
    abstract fun width() : Int

    /**
     * 设置布局高
     * */
    abstract fun height() : Int

    /**
     * 设置是否控制返回键
     * */
    abstract fun cancelable() : Boolean

    /**
     * 设置是否控制dialog外部UI
     * */
    abstract fun canceledOnTouchOutside() : Boolean

    override fun onStart() {
        super.onStart()
        dialog?.let { dialog ->
            dialog.setCancelable(cancelable())
            dialog.setCanceledOnTouchOutside(canceledOnTouchOutside())
            dialog.window?.let { window ->
                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                val attr = window.attributes
                attr.width = if (width() > 0) px2dp(width()) else width()
                attr.height = if (height() > 0) px2dp(height()) else height()
                attr.gravity = Gravity.CENTER
                window.attributes = attr
            }
        }
    }
    fun show(fragmentManager: FragmentManager){
        show(fragmentManager,tag)
    }

    open fun px2dp(px: Int): Int {
        return context!!.resources.getDimensionPixelSize(R.dimen.base_dp) * px
    }
}