package com.sdg.bec

import android.app.Application
import com.dimeno.network.Network
import com.dimeno.network.config.NetConfig
import org.litepal.LitePal

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LitePal.initialize(this)
        Network.init(this, NetConfig.Builder().baseUrl("https://fanyi-api.baidu.com/api/trans/vip/translate").build())
    }
}