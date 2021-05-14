package com.lindroy.androidxutilskt.statics.startresult

import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment

/**
 * @author Lin
 * @date 2021/5/14
 * @function
 */

internal typealias ResultCallback = (resultCode: Int, data: Intent?) -> Unit

private const val RC_CODE = 100

class ResultFragment : Fragment() {

    private var callback: ResultCallback? = null

    fun requestNow(intent: Intent,cb:ResultCallback){
        callback = cb
        startActivityForResult(intent,RC_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_CODE) {
            callback?.invoke(resultCode, data)
        }
    }
}