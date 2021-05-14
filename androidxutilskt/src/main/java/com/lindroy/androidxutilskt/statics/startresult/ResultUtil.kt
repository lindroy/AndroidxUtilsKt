package com.lindroy.androidxutilskt.statics.startresult

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

/**
 * @author Lin
 * @date 2021/5/14
 * @function
 */
object ResultUtil {

    private const val TAG = "ResultFragment"

    fun startActForResult(activity: FragmentActivity, intent: Intent, callback: ResultCallback) {
        createFragment(activity.supportFragmentManager).requestNow(intent,callback)
    }

    fun startActForResult(fragment: Fragment, intent: Intent, callback: ResultCallback) {
        createFragment(fragment.childFragmentManager).requestNow(intent,callback)
    }

    private fun createFragment(fm: FragmentManager): ResultFragment {
        val existedFragment = fm.findFragmentByTag(TAG)
        return if (existedFragment != null) {
            existedFragment as ResultFragment
        } else {
            ResultFragment().let {
                fm.beginTransaction().add(it, TAG).commitNow()
                it
            }
        }
    }
}