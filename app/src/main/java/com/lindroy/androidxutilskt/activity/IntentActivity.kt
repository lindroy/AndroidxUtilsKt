package com.lindroy.androidxutilskt.activity

import android.annotation.SuppressLint
import com.lindroy.androidxutilskt.R
import com.lindroy.androidxutilskt.base.BaseActivity
import com.lindroy.androidxutilskt.extensions.*
import com.lindroy.androidxutilskt.statics.IntentUtil
import com.lindroy.androidxutilskt.statics.permission.PermissionUtil
import kotlinx.android.synthetic.main.activity_intent.*

class IntentActivity(override val contentViewId: Int = R.layout.activity_intent) : BaseActivity() {


    override fun initView() {
        initToolBar(R.string.util_intent)
    }


    @SuppressLint("MissingPermission")
    override fun initOnClick() {
        super.initOnClick()
        //打开系统设置
        btnSystemSetting.setOnClickListener {
            IntentUtil.launchSystemSetting(mContext)
        }
        //打开wifi设置
        btnWiFiSetting.setOnClickListener {
            IntentUtil.launchWifiSetting(mContext)
        }
        //打开拨号面板]
        btnDialPage.setOnClickListener {
            IntentUtil.launchDialPage(mContext)
        }
        //直接拨打电话
        btnCallPhone.setOnClickListener {
            PermissionUtil.callPhone(this) { allGranted, deniedList ->
                IntentUtil.callPhone(this, "10086")
            }
        }
        //打开浏览器
        btnBrowser.setOnClickListener {
            IntentUtil.launchBrowse(mContext, "http://www.baidu.com/") {
                if (it) {
                    shortToast("成功打开浏览器选择面板")
                } else {
                    shortToast("打开浏览器选择面板失败")
                }
            }
        }
        //启动相机
        btnCamera.setOnClickListener {
            PermissionUtil.launchCamera(this) { allGranted, deniedList ->
                IntentUtil.launchCamera(this, 100)
            }
        }
        //发送短信
        btnSendSMS.setOnClickListener {
            PermissionUtil.sendSMS(this){allGranted, deniedList ->
                IntentUtil.sendSMS(mContext, "10086", "这是一条短信")
            }
        }
    }
}