package com.lindroy.androidxutilskt.activity.statusbar

import android.content.Intent
import com.lindroy.androidxutilskt.R
import com.lindroy.androidxutilskt.base.BaseActivity
import com.lindroy.androidxutilskt.extensions.statusbar.*
import com.youngfeng.snake.annotations.EnableDragToClose
import kotlinx.android.synthetic.main.activity_status_bar_change_color.*

/**
 * @author Lin
 * @date 2019/3/1
 * @function 改变状态栏颜色
 * @Description
 */
@EnableDragToClose
class StatusBarChangeColorActivity(override val contentViewId: Int = R.layout.activity_status_bar_change_color) :
    BaseActivity() {

    override fun initView() {
//        initToolBar("改变状态栏颜色", toolBarColor = android.R.color.transparent)
//        toolBar.setPadding(0, getStatusBarHeight(), 0, 0)
        setTransParentStatusBar()
        setResult(1110, Intent().apply { putExtra("tag","hello") })
    }

    override fun initOnClick() {
        super.initOnClick()
        btnTransparent.setOnClickListener {
            setStatusBarColorRes(android.R.color.transparent)
        }
        btnWhite.setOnClickListener {
            setStatusBarColorRes(android.R.color.white)
        }
        btnBlack.setOnClickListener {
            setStatusBarColorRes(android.R.color.black)
        }
        btnGradient.setOnClickListener {
            setGradientStatusBar(R.drawable.shape_gradient_bar)
        }
        btnLight.setOnClickListener {
            setStatusBarLightMode()
        }
        btnDark.setOnClickListener {
            setStatusBarDarkMode()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //不能在onDestroy方法中setResult
    }

}
