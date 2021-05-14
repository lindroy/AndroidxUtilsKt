package com.lindroy.androidxutilskt.statics.permission

import android.Manifest
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.lindroy.androidxutilskt.statics.startresult.ResultFragment
import com.lindroy.androidxutilskt.statics.startresult.ResultUtil

/**
 * @author Lin
 * @date 2021/3/15
 * @function 权限申请工具类
 */
object PermissionUtil {

    private const val TAG = "InvisibleFragment"

    fun request(
        activity: FragmentActivity,
        vararg permissions: String,
        callback: PermissionCallback
    ) {
        createFragment(activity.supportFragmentManager).requestNow(callback, *permissions)
    }

    fun request(fg: Fragment, vararg permissions: String, callback: PermissionCallback) {
        createFragment(fg.childFragmentManager).requestNow(callback, *permissions)
    }

    private fun createFragment(fm: FragmentManager): InvisibleFragment {
        val existedFragment = fm.findFragmentByTag(TAG)
        return if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            InvisibleFragment().let {
                fm.beginTransaction().add(it, TAG).commitNow()
                it
            }
        }
    }

    /**
     * 获取读取存储权限
     */
    fun storage(fg: Fragment, callback: PermissionCallback) {
        request(
            fg = fg, permissions = arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ), callback = callback
        )
    }

    /**
     * 获取读取存储权限
     */
    fun storage(activity: FragmentActivity, callback: PermissionCallback) {
        request(
            activity, permissions = arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ), callback = callback
        )
    }

    /**
     * 拨打电话
     */
    fun callPhone(fg: Fragment, callback: PermissionCallback) {
        request(fg = fg, permissions = arrayOf(Manifest.permission.CALL_PHONE), callback = callback)
    }

    /**
     * 拨打电话
     */
    fun callPhone(activity: FragmentActivity, callback: PermissionCallback) {
        request(activity, permissions = arrayOf(Manifest.permission.CALL_PHONE), callback)
    }

    /**
     * 启动相机
     */
    fun launchCamera(fg: Fragment, callback: PermissionCallback) {
        request(
            fg = fg, permissions = arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ), callback = callback
        )
    }

    /**
     * 启动相机
     */
    fun launchCamera(activity: FragmentActivity, callback: PermissionCallback) {
        request(
            activity = activity, permissions = arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ), callback = callback
        )
    }

    /**
     * 发送短信
     */
    fun sendSMS(fg: Fragment, callback: PermissionCallback) {
        request(fg = fg, permissions = arrayOf(Manifest.permission.SEND_SMS), callback = callback)
    }

    /**
     * 发送短信
     */
    fun sendSMS(activity: FragmentActivity, callback: PermissionCallback) {
        request(
            activity = activity,
            permissions = arrayOf(Manifest.permission.SEND_SMS),
            callback = callback
        )
    }

    /**
     * 读取通讯录
     */
    fun readContacts(activity: FragmentActivity, callback: PermissionCallback) {
        request(
            activity = activity,
            permissions = arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_CONTACTS
            ),
            callback = callback
        )
    }

    /**
     * 读取通讯录
     */
    fun readContacts(fg: Fragment, callback: PermissionCallback) {
        request(
            fg,
            permissions = arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_CONTACTS
            ),
            callback = callback
        )
    }
}