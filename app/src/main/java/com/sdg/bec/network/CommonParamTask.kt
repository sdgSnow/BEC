package com.sdg.bec.network

import com.dimeno.network.callback.LoadingCallback
import com.dimeno.network.callback.RequestCallback
import com.dimeno.network.task.GetTask
import com.sdg.bec.constant.Path

open class CommonParamTask(callback: RequestCallback<*>) : GetTask(callback) {
    override fun getApi(): String {
        return ""
    }

    override fun onSetupParams(vararg params: Any?) {
        put("appid",Path.APPID)
        put("to","zh")
        put("from","en")
    }
}