package com.sdg.bec.view

import com.sdg.bec.db.Words
import com.sdg.common.BaseView

interface VWords : BaseView {
    fun onSuccess(loadWords: List<Words>)
    fun onError()
}