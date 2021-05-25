package com.lindroy.androidxutilskt.activity

import android.view.View
import com.lindroy.androidxutilskt.R
import com.lindroy.androidxutilskt.base.BaseActivity
import com.lindroy.androidxutilskt.extensions.*
import com.lindroy.androidxutilskt.extensions.px2dp
import kotlinx.android.synthetic.main.activity_app_info.*

class AppInfoActivity(override val contentViewId: Int = R.layout.activity_app_info) : BaseActivity() {
    override fun initView() {
        initToolBar(R.string.util_app_info)
        tvVersionName.text = "版本名：${getAppVersionName()}"
        tvVersionCode.text = "版本号：${getAppVersionCode()}"
        tvAppSize.text = "应用大小：${getAppSize()}"
        ivIcon.setImageDrawable(getAppIcon())
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 shl 30) - 1, View.MeasureSpec.AT_MOST)
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 shl 30) - 1, View.MeasureSpec.AT_MOST)
        ivIcon.measure(widthMeasureSpec, heightMeasureSpec)
        "ivIcon.viewHeight=${px2dp(ivIcon.measuredHeight)},ivIcon.viewWidth=${px2dp(ivIcon.measuredWidth)}"
    }

}