@file:JvmName("BarUtil")

package com.lindroy.androidxutilskt.extensions

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import com.lindroy.androidxutilskt.AndUtil


/**
 * @author Lin
 * @date 2019/6/11
 * @function 虚拟导航栏工具类
 * @Description
 */

/**
 * 手机是否有虚拟导航栏
 */
val hasNavBar
    @JvmName("hasNavBar")
    get() = navBarResId != 0

/**
 * 当前虚拟导航栏是否显示
 */
val Activity.isNavBarShowed: Boolean
    get() = this.window.isNavBarShowed

/**
 * 当前虚拟导航栏是否显示
 */
val Window.isNavBarShowed: Boolean
    get() {
        val viewGroup = decorView as ViewGroup? ?: return false
        return (0 until viewGroup.childCount).firstOrNull {
            viewGroup.getChildAt(it).id != View.NO_ID
                    && AndUtil.appContext.resources.getResourceEntryName(viewGroup.getChildAt(it).id) == RES_NAME_NAV_BAR
        } != null
    }

/**
 * 当前虚拟导航栏是否隐藏
 */
val Window.isNavBarHidden: Boolean
    get() = !isNavBarShowed

/**
 * 当前虚拟导航栏是否隐藏
 */
val Activity.isNavBarHidden: Boolean
    get() = window.isNavBarHidden




/**
 * 获取虚拟导航栏的高度，必须在布局绘制完成之后调用才能获取到正确的值（可以在onWindowFocusChanged()中调用）
 * 单位为px
 */
val navBarHeight: Int
    get() {
        val resourceId = navBarResId
        return if (resourceId != 0) {
            getResDimenPx(resourceId)
        } else 0
    }

/**
 * 设置导航栏颜色
 */
var Window.navBarColor: Int
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = navigationBarColor
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    set(@ColorInt value) {
        navigationBarColor = value
    }

/**
 * 设置导航栏颜色
 */
var Activity.navBarColor: Int
    get() = window.navBarColor
    set(@ColorInt value) {
        window.navBarColor = value
    }

/**
 * 设置导航栏颜色
 */
fun Window.setNavBarColorRes(@ColorRes colorId: Int) {
    navBarColor = getResColor(colorId)
}

/**
 * 设置导航栏颜色
 */
fun Activity.setNavBarColorRes(@ColorRes colorId: Int) {
    navBarColor = getResColor(colorId)
}

private const val RES_NAME_NAV_BAR = "navigationBarBackground"

private val navBarResId
    get() = AndUtil.appContext.resources.getIdentifier(
        "navigation_bar_height",
        "dimen", "android"
    )