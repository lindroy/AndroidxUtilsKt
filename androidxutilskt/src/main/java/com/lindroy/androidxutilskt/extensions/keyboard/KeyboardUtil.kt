@file:JvmName("KeyboardUtil")

package com.lindroy.androidxutilskt.extensions.keyboard

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * @author Lin
 * @date 2018/10/26
 * @function 软键盘工具类
 * @Description
 */

/**
 * 打开软键盘
 */
fun View.showKeyboard(): Boolean {
    val imm = context
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    requestFocus()
    return imm.showSoftInput(this, InputMethodManager.RESULT_UNCHANGED_SHOWN)
}

/**
 * 关闭软键盘
 */
fun View.hideKeyboard(): Boolean {
    val imm = context
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    return imm.hideSoftInputFromWindow(this.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
}

/**
 * 根据当前软键盘的状态做取反操作
 */
fun View.toggleKeyboard() {
    val imm = context
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.RESULT_UNCHANGED_SHOWN)
}

/**
 * 设置软键盘的显示和隐藏监听事件
 */
/*
fun View.addKeyboardStatusWatcher(callback: ((isShowed: Boolean, keyboardHeight: Int) -> Unit)) =
    with(KeyboardStatusWatcher(this)) {
        addKeyboardStatusWatcher(callback)
        this
    }

fun View.removeKeyboardStatusWatcher(){
    KeyboardStatusWatcher(this).removeKeyboardStatusWatcher(this)
}*/

