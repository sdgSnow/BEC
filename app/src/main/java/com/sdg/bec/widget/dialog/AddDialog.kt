package com.sdg.bec.widget.dialog

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.sdg.bec.R

class AddDialog : BaseDialogFragment() {

    private var cancle = true
    private var cancleTouchOutside = true
    private var title: String? = null
    private var yes: String? = "确定"
    private var no: String? = "取消"
    private var callback: AddCallback? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cnName = view.findViewById<EditText>(R.id.cnName)
        val enName = view.findViewById<EditText>(R.id.enName)
        view.findViewById<TextView>(R.id.tvTitle).apply {
            text = title
        }
        view.findViewById<TextView>(R.id.tvYes).apply {
            text = yes
        }
        view.findViewById<TextView>(R.id.tvNo).apply {
            text = no
        }
        view.findViewById<TextView>(R.id.tvYes).apply {
            setOnClickListener {
                dismiss()
                callback?.add(cnName.text.toString().trim(),enName.text.toString().trim())
            }
        }
        view.findViewById<TextView>(R.id.tvNo).apply {
            setOnClickListener {
                dismiss()
                callback?.no()
            }
        }
    }

    override fun layout(): Int = R.layout.dialog_normal

    override fun width(): Int = ViewGroup.LayoutParams.MATCH_PARENT

    override fun height(): Int = ViewGroup.LayoutParams.WRAP_CONTENT

    override fun cancelable(): Boolean = cancle

    override fun canceledOnTouchOutside(): Boolean = cancleTouchOutside

    fun setCancle(cancle: Boolean): AddDialog {
        this.cancle = cancle
        return this
    }

    fun setCancleTouch(cancleTouchOutside: Boolean): AddDialog {
        this.cancleTouchOutside = cancleTouchOutside
        return this
    }

    fun setTitle(title : String): AddDialog {
        this.title = title
        return this
    }

    fun setYes(yes : String): AddDialog {
        this.yes = yes
        return this
    }

    fun setNo(no : String): AddDialog {
        this.no = no
        return this
    }

    fun setCallback(callback:AddCallback): AddDialog {
        this.callback = callback
        return this
    }
}