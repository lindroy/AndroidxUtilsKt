package com.lindroy.androidxutilskt.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import com.lindroy.androidxutilskt.extensions.*

const val SCREEN_TAG = "ScreenTag"

class ScreenActionReceiver(private val mContext: Context) : BroadcastReceiver() {
    private var isRegistered = false

    private var screenActionListener: ScreenActionListener? = null

    override fun onReceive(context: Context, intent: Intent) {

        when (intent.action) {
            Intent.ACTION_SCREEN_ON -> {
                //亮屏
                screenActionListener?.onScreenOn()
            }
            Intent.ACTION_SCREEN_OFF -> {
                //暗屏
                screenActionListener?.onScreenOff()

            }
            Intent.ACTION_USER_PRESENT -> {
                //解锁
                screenActionListener?.onScreenUnLocked()
            }
        }
        Log.d(SCREEN_TAG, "isScreenLocked：$isScreenLocked")
        Log.d(SCREEN_TAG, "isScreenUnlocked：$isScreenUnlocked")
        Log.d(SCREEN_TAG, "isScreenOn：$isScreenOn")
        Log.d(SCREEN_TAG, "isScreenOff：$isScreenOff")
    }

    fun registerScreenActionReceiver() {
        if (!isRegistered) {
            Log.d(SCREEN_TAG, "注册锁屏监听广播")
            isRegistered = true
            mContext.registerReceiver(this, with(IntentFilter()) {
                addAction(Intent.ACTION_SCREEN_ON)
                addAction(Intent.ACTION_SCREEN_OFF)
                addAction(Intent.ACTION_USER_PRESENT)
                this
            })
        }
    }

    fun unRegisterScreenActionReceiver() {
        Log.d(SCREEN_TAG, "解绑锁屏监听广播")
        if (isRegistered) {
            isRegistered = false
            screenActionListener = null
            mContext.unregisterReceiver(this)
        }
    }

    fun setScreenActionListener(listener: ScreenActionListener) {
        screenActionListener = listener
    }

    interface ScreenActionListener {
        fun onScreenOn()
        fun onScreenOff()
        fun onScreenUnLocked()
    }
}
