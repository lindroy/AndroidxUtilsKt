package com.lindroy.androidxutilskt.activity.statusbar

import android.util.Log
import com.lindroy.androidxutilskt.R
import com.lindroy.androidxutilskt.base.BaseActivity
import com.lindroy.androidxutilskt.extensions.launchActivity
import com.lindroy.androidxutilskt.extensions.launchActivityForResult
import com.lindroy.androidxutilskt.extensions.px2dp
import com.lindroy.androidxutilskt.extensions.shortToast
import com.lindroy.androidxutilskt.extensions.statusbar.statusBarHeight
import com.youngfeng.snake.annotations.EnableDragToClose
import kotlinx.android.synthetic.main.activity_status_bar.*

@EnableDragToClose
class StatusBarActivity(override val contentViewId: Int = R.layout.activity_status_bar) :
    BaseActivity() {
    override fun initView() {
        initToolBar(R.string.util_status_bar)
    }

    override fun initOnClick() {
        super.initOnClick()
        btnHeight.setOnClickListener {
            shortToast("状态栏高度：${px2dp(statusBarHeight)}dp")
        }
        btnChangeColor.setOnClickListener {
//            launchActivity<StatusBarChangeColorActivity>()
            launchActivityForResult<StatusBarChangeColorActivity> { resultCode, data ->
                Log.d("Tag", "resultCode=$resultCode")
                Log.d("Tag", "data=${data?.getStringExtra("tag")}")
            }
        }
        btnImage.setOnClickListener {
            launchActivity<StatusBarImageActivity>()
        }
        btnSlide.setOnClickListener {
            launchActivity<StatusBarSlideActivity>()
        }

    }
}