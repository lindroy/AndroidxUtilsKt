package com.lindroy.androidxutilskt.base

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.lindroy.androidxutilskt.R
import kotlinx.android.synthetic.main.toolbar.view.*




/**
 * @author Lin
 * @date 2019/2/27
 * @function 基类Activity
 * @Description
 */
abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var mContext: Context

    //默认值为0
    abstract val contentViewId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
//        AppManager.addActivity(this)
        initBefore()
        setContentView(contentViewId)
        initView()
        initOnListener()

    }

    open fun initOnListener() {
        initOnClick()
    }


    open fun initOnClick() {

    }

    open fun initBefore() {

    }

    abstract fun initView()


    fun initToolBar(
        title: String = getString(R.string.app_name),
        isShowArrow: Boolean = true, @ColorRes toolBarColor: Int = R.color.colorPrimary
    ) {
        val toolView = window.decorView
        toolView.toolBar.title = title
        toolView.toolBar.setBackgroundColor(ContextCompat.getColor(mContext, toolBarColor))
        //ToolBar的属性设置要在setSupportActionBar方法之前调用
        setSupportActionBar(toolView.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(isShowArrow)
    }

    fun initToolBar(@StringRes stringId: Int, isShowArrow: Boolean = true) {
        initToolBar(mContext.getString(stringId), isShowArrow)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            //为了兼容WebView页面的二级页面回退时间，不能在这里处理返回按钮的点击事件
            android.R.id.home -> finish()
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
//        AppManager.removeActivity(this)
    }

}