package com.sdg.library

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class PermissionFragment : Fragment(),RequestPermissionCallback {

    private var callback: Callback? = null

    companion object{
        //被拒绝权限集
        private val deniedPermissions: MutableList<String> = ArrayList()
        //未申明权限集
        private val notDeclaredPermissions: MutableList<String> = ArrayList()
    }

    override fun exeRequestPermissions(callback: Callback?, permissions: Array<String>) {
        this.callback = callback
        deniedPermissions.clear()
        notDeclaredPermissions.clear()
        if(host != null){
            launcher.launch(permissions)
        }else{
            lifecycle.addObserver(object : LifecycleEventObserver{
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    if(event == Lifecycle.Event.ON_CREATE){
                        launcher.launch(permissions)
                    }
                }
            })
        }

    }

    private val launcher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ result ->
        for((permission,value) in result){
            if (!value){
                if(manifestPermissions().contains(permission)){
                    deniedPermissions.add(permission)
                }else{
                    notDeclaredPermissions.add(permission)
                }
            }
        }
        if(deniedPermissions.isEmpty() && notDeclaredPermissions.isEmpty()){
            callback?.onGrant()
        }else{
            if(notDeclaredPermissions.isNotEmpty()){
                callback?.onNotDeclared(notDeclaredPermissions.toTypedArray())
            }else{
                callback?.onDeny(deniedPermissions.toTypedArray())
            }
        }

    }

    /**
     * get all permissions in manifest
     *
     * @return permissions set
     */
    private fun manifestPermissions(): Set<String> {
        var manifestPermissions: Set<String>? = null
        var packageInfo: PackageInfo? = null
        try {
            context?.let { ctx ->
                packageInfo = ctx.applicationContext?.packageManager?.getPackageInfo(
                    ctx.packageName,
                    PackageManager.GET_PERMISSIONS
                )
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        packageInfo?.let { info ->
            val permissions = info.requestedPermissions
            if (permissions != null && permissions.isNotEmpty()) {
                manifestPermissions = HashSet(listOf(*permissions))
            }
        }
        return manifestPermissions ?: HashSet(0)
    }

}