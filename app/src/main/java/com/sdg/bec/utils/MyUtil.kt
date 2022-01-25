package com.sdg.bec.utils

import com.sdg.bec.constant.Constant
import com.sdg.bec.constant.Path
import com.sdg.bec.db.Words
import com.sdg.bec.eventbus.AddWordsEvent
import org.greenrobot.eventbus.EventBus
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MyUtil {

    companion object{
        fun createDirectoryIfNotExist(path: String?): Boolean {
            val file = File(path)
            if (!file.exists()) {
                //首次创建文件夹则记录时间
                if (file.mkdirs()) {
                    return true
                }
            }
            return false
        }

        fun getUUID(): String {
            val uuid = UUID.randomUUID()
            return uuid.toString()
        }

        fun getTime():String{
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            return format.format(System.currentTimeMillis())
        }

        fun addWord(cnName:String,enName:String){
            var important = Constant.Important.primary
            var classify = Constant.SentenceClassify.life
            var status = Constant.Status.add
            var time = getTime()
            Words(getUUID(),important,"type",enName,cnName,classify,time,status).save()
            EventBus.getDefault().postSticky(AddWordsEvent())
        }

        /**
         * 随机码
         * */
        fun getSalt():String{
            return Random().nextInt().toString()
        }

        /**
         * Step1. 拼接字符串1：绩效考核
        拼接appid=2015063000000001+q=apple+salt=1435660288+密钥=12345678得到字符串1：“2015063000000001apple143566028812345678”
        Step2. 计算签名：（对字符串1做md5加密）
        sign=md5(2015063000000001apple143566028812345678)，得到sign=f89f9594663708c1605f3d736d01d2d4
         *
         * */
        fun getSign(content: String): String {
            var sign = Path.APPID + content + getNum(1) + Path.SECRCT
            return MD5Util.stringToMD5(sign)
        }

        /**
         * 随机数 (根据百度的要求需要一个随机数)
         */

        fun getNum(a:Int) : String{
            var r = Random()
            var r1 = 0
            for (index in 0..4){
                r1 = r.nextInt(100)
            }
            return r1.toString()
        }

    }
}