package com.lindroy.androidxutilskt.activity.statusbar

import com.lindroy.androidxutilskt.R
import com.lindroy.androidxutilskt.base.BaseActivity
import com.lindroy.androidxutilskt.extensions.statusbar.setTransParentStatusBar
import com.youngfeng.snake.annotations.EnableDragToClose
import kotlinx.android.synthetic.main.activity_status_bar_slide.*

/**
 * @author Lin
 * @date 2019/3/1
 * @function 配合侧滑返回上一页面（Snake库）使用
 * @Description
 */

@EnableDragToClose
class StatusBarSlideActivity(override val contentViewId: Int = R.layout.activity_status_bar_slide) : BaseActivity() {

    override fun initView() {
        initToolBar("配合侧滑使用", toolBarColor = android.R.color.holo_red_light)
        setTransParentStatusBar()

        tvDesc.text = """
侧滑返回上一页时状态栏无法跟随移动，所以如果前后页面状态栏颜色不一致，可以做如下操作：
1、根布局添加如下属性：android:fitsSystemWindows="true"；
2、将根布局背景色设为标题栏颜色或者原本要设置的状态栏颜色；
3、设置透明状态栏。
        """
    }
}
