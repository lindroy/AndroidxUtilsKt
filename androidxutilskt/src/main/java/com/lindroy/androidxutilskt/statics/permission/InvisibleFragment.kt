package com.lindroy.androidxutilskt.statics.permission

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment


/**
 * @author Lin
 * @date 2021/3/15
 * @function
 */

internal typealias PermissionCallback = (allGranted:Boolean,deniedList:List<String>) -> Unit

internal class InvisibleFragment : Fragment(){

    private var callback: PermissionCallback? = null

    fun requestNow(cb: PermissionCallback, vararg permissions:String){
        callback = cb
        requestPermissions(permissions,1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1){
            val deniedList = ArrayList<String>()
            grantResults.forEachIndexed{ index, result ->
                if (result == PackageManager.PERMISSION_DENIED){
                    deniedList.add(permissions[index])
                }
            }
            callback?.let { it(deniedList.isEmpty(),deniedList) }
        }
    }
}