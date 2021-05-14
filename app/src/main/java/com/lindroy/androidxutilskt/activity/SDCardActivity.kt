package com.lindroy.androidxutilskt.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lindroy.androidxutilskt.R
import com.lindroy.androidxutilskt.base.BaseActivity
import com.lindroy.androidxutilskt.extensions.*
import com.youngfeng.snake.annotations.EnableDragToClose
import kotlinx.android.synthetic.main.activity_sd_card.*

/**
 * @author Lin
 * @date 2021/5/14
 * @function SD卡工具类
 * @Description
 */
@EnableDragToClose
class SDCardActivity(override val contentViewId: Int = R.layout.activity_sd_card) : BaseActivity() {

    override fun initView() {
        initToolBar(R.string.util_sdcard)
        tvMounted.text = "SD卡是否可用：$isSDCardMounted"
        tvPath.text = "SD卡路径：${sdCardPath}"
        tvTotalSize.text = "SD卡的总大小：${sdCardTotalSize / (1024 * 1024 * 1024)}G"
        tvAvailableSize.text = "SD卡可用空间大小：${sdCardAvailableSize / (1024 * 1024 * 1024)}G"
    }
}