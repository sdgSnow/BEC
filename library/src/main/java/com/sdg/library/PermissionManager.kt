package com.sdg.library

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.sdg.library.Constants.Companion.FRAGMENT_TAG
import com.sdg.library.Utils.getFragmentActivity

/**
 * PermissionManager
 * Created by wangzhen on 2020/4/15.
 */
object PermissionManager {

    @JvmStatic
    fun request(
        fragment: Fragment?,
        callback: Callback?,
        vararg permissions: String
    ) {
        request(fragment?.activity, callback, *permissions)
    }

    @JvmStatic
    fun request(context: Context?, callback: Callback?, vararg permissions: String) {
        context?.let { ctx ->
            request(
                getFragmentActivity(ctx) as FragmentActivity, callback, *permissions
            )
        }
    }

    @JvmStatic
    fun request(
        activity: FragmentActivity,
        callback: Callback?,
        vararg permissions: String
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermission(activity, callback, *permissions)
        } else {
            callback?.onGrant()
        }
    }

    /**
     * get intent to application details setting page
     *
     * @param context context
     * @return intent
     */
    @JvmStatic
    fun getSettingIntent(context: Context): Intent {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:" + context.packageName)
        return intent
    }

    /**
     * start permission request
     *
     * @param activity    activity
     * @param callback    callback
     * @param permissions permissions
     */
    private fun requestPermission(
        activity: FragmentActivity,
        callback: Callback?,
        vararg permissions: String
    ) {
        val manager = activity.supportFragmentManager
        val tag = manager.findFragmentByTag(FRAGMENT_TAG)
        val fragment: PermissionFragment
        if (tag is RequestPermissionCallback) {
            fragment = tag as PermissionFragment
        } else {
            fragment = PermissionFragment()
            manager.beginTransaction().add(fragment, FRAGMENT_TAG).commitAllowingStateLoss()
        }
        (fragment as RequestPermissionCallback).exeRequestPermissions(callback,arrayOf(*permissions))
    }
}