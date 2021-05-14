package com.lindroy.androidxutilskt.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.lindroy.androidxutilskt.R
import com.lindroy.androidxutilskt.base.BaseActivity
import com.lindroy.androidxutilskt.extensions.*
import com.youngfeng.snake.annotations.EnableDragToClose
import kotlinx.android.synthetic.main.activity_view.*


/**
 * @author Lin
 * @date 2021/5/14
 * @function View相关工具类
 * @Description
 */
@EnableDragToClose
class ViewActivity(override val contentViewId: Int = R.layout.activity_view) : BaseActivity() {

    @SuppressLint("SetTextI18n")
    override fun initView() {
        initToolBar(R.string.util_view)
        tvWidth.text = "设置View的宽度：${screenWidth}px"
        tvHeight.text = "设置View的高度：${(dp2px(150))}px"
//        "llRoot.viewHeight=${px2dp(llRoot.viewHeight)},llRoot.viewWidth=${px2dp(llRoot.viewWidth)}".d()
//        "frameLayout.viewHeight=${px2dp(frameLayout.viewHeight)},frameLayout.viewWidth=${px2dp(frameLayout.viewWidth)}".d()
    }

    override fun initOnClick() {
        super.initOnClick()
        btnVisible.setOnClickListener { frameLayout.setVisible() }
        btnInvisible.setOnClickListener { frameLayout.setInvisible() }
        btnGone.setOnClickListener { frameLayout.setGone() }
        btnToBitmap.setOnClickListener {
            ivBitmap.setImageBitmap(frameLayout.toBitmap())
        }
    }

    override fun initOnListener() {
        super.initOnListener()
        //动态设置宽度
        sbWidth.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvWidth.text = "设置View的宽度：${(screenWidth * progress) / 100}px"
                frameLayout.setWidth((screenWidth * progress) / 100)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        //动态设置高度
        sbHeight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvHeight.text = "设置View的高度：${(dp2px(150) * progress) / 100}px"
                frameLayout.setHeight((dp2px(150) * progress) / 100)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }

}