package com.lindroy.androidxutilskt.base

import android.app.Application
import com.youngfeng.snake.Snake

/**
 * @author Lin
 * @date 2021/5/14
 * @function
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Snake.init(this)
    }
}