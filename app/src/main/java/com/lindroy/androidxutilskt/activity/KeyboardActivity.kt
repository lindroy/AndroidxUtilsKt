package com.lindroy.androidxutilskt.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lindroy.androidxutilskt.R
import com.lindroy.androidxutilskt.base.BaseActivity
import com.lindroy.androidxutilskt.extensions.keyboard.KeyboardStatusWatcher
import com.lindroy.androidxutilskt.extensions.keyboard.*
import com.lindroy.androidxutilskt.extensions.setHeight
import com.lindroy.androidxutilskt.extensions.shortToast
import com.youngfeng.snake.annotations.EnableDragToClose
import kotlinx.android.synthetic.main.activity_keyboard.*

/**
 * @author Lin
 * @date 2021/5/14
 * @function 键盘工具类
 * @Description
 */
@EnableDragToClose
class KeyboardActivity(override val contentViewId: Int = R.layout.activity_keyboard) : BaseActivity() {

    override fun initView() {
        initToolBar(R.string.util_keyboard)
    }

    override fun initOnClick() {
        super.initOnClick()
        btnShow.setOnClickListener { editText.showKeyboard() }
        btnHide.setOnClickListener { editText.hideKeyboard() }
        btnToggle.setOnClickListener { editText.toggleKeyboard() }

        /*llRoot.addOnKeyboardStatusWatcher { hasShow, keyboardHeight ->
            "软键盘状态：${hasShow}，键盘高度：$keyboardHeight".d()
            "软键盘是否打开：${llRoot.isKeyboardShowed}".d()
        }*/


        val keyboardStatus = KeyboardStatusWatcher(llRoot)
        //监听软键盘状态
        keyboardStatus.addKeyboardStatusWatcher { isShowed, keyboardHeight ->
            val status = if (isShowed) "软键盘显示，高度为${keyboardStatus.keyboardHeight}px" else "软键盘收起"
            shortToast(status)
            flKeyboard.setHeight(keyboardHeight)
        }
        //软键盘高度
        keyboardStatus.keyboardHeight
        //软键盘是否显示
        keyboardStatus.isKeyboardShowed
        //软键盘是否隐藏
        keyboardStatus.isKeyboardHidden

    }
}