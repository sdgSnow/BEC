package com.sdg.bec.network

import com.dimeno.network.callback.RequestCallback
import com.dimeno.network.task.GetTask

class TranslateTask(callback:RequestCallback<*>) : GetTask(callback) {
    override fun getApi(): String {
        return "/"
    }
}