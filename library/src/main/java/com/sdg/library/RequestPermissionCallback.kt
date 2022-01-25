package com.sdg.library

interface RequestPermissionCallback {
    fun exeRequestPermissions(callback: Callback?, permissions: Array<String>)
}