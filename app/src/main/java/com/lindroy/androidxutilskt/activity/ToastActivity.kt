package com.lindroy.androidxutilskt.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lindroy.androidxutilskt.R
import com.lindroy.androidxutilskt.base.BaseActivity
import com.lindroy.androidxutilskt.extensions.*
import kotlinx.android.synthetic.main.activity_toast.*

/**
 * @author Lin
 * @date 2021/5/14
 * @function Toast工具类
 * @Description
 */
class ToastActivity(override val contentViewId: Int = R.layout.activity_toast) : BaseActivity() {
    override fun initView() {
        initToolBar(R.string.util_toast)
    }

    override fun initOnClick() {
        super.initOnClick()
        btnShortToast.setOnClickListener { shortToast("短Toast") }
        btnLongToast.setOnClickListener { longToast("长Toast") }
    }
}