package com.sdg.library

interface Callback {
    /**
     * all permissions are granted
     */
    fun onGrant()

    /**
     * permissions are denied or refused
     * @param deniedPermissions   denied permissions
     */
    fun onDeny(deniedPermissions: Array<String?>?)

    /**
     * permissions not declared in manifest
     * @param permissions permissions
     */
    fun onNotDeclared(permissions: Array<String?>?)
}