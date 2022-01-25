package com.sdg.bec

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.ToastUtils
import com.dimeno.permission.PermissionManager
import com.dimeno.permission.callback.PermissionCallback
import com.github.kongpf8848.tkpermission.permissions
import com.sdg.bec.constant.Constant
import com.sdg.bec.constant.Path
import com.sdg.bec.databinding.ActivitySplashBinding
import com.sdg.bec.presenter.PSplash
import com.sdg.bec.utils.MyUtil
import com.sdg.bec.view.VSplash
import com.sdg.ktques.base.BaseBindingActivity
import com.sdg.library.Callback
import com.sdg.library.PermissionManager.request
import java.io.File

class SplashActivity : BaseBindingActivity<ActivitySplashBinding,VSplash,PSplash>(),VSplash {

    override fun createPresenter(): PSplash? = PSplash()

    override fun initViews(savedInstanceState: Bundle?) {

    }

    override fun initData() {
//        PermissionManager.request(
//            this,
//            object : PermissionCallback {
//                override fun onGrant(permissions: Array<String>) {
//                    val mkdirs = File(Path.DB_PATH).mkdirs()
//                    val createOrExistsDir = FileUtils.createOrExistsFile(Path.DB_PATH)
//                    gotoActivity(MainActivity::class.java,true)
//                }
//
//                override fun onDeny(deniedPermissions: Array<String>, neverAskPermissions: Array<String>) {
//                    ToastUtils.showShort("为了正常使用，请授予权限")
//                }
//
//                override fun onNotDeclared(permissions: Array<String>) {}
//            },
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//        )
    }

    fun requestP(view: View) {
        request(
            this,
            object : Callback {
                override fun onGrant() {
                    ToastUtils.showShort("申请成功")
                }

                override fun onDeny(deniedPermissions: Array<String?>?) {
                    ToastUtils.showShort("申请失败")
                }

                override fun onNotDeclared(permissions: Array<String?>?) {

                }
            },
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA
        )
    }

}