package com.lindroy.androidxutilskt

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.lindroy.androidxutilskt.extensions.setSpDefaultFile
import java.lang.reflect.InvocationTargetException

/**
 * @author Lin
 * @date 2021/5/14
 * @function
 */
@SuppressLint("PrivateApi")
object AndUtil {

    private var app: Application? = null

    /**
     * 如果反射获取Application失效的话，可以使用此初始化方法
     */
    @JvmStatic
    fun init(application: Application): Config {
        app = application
        return Config.build()
    }

    val application by lazy {
       if (app != null){
            app!!
       }else{
           try {
               app = Class.forName("android.app.ActivityThread")
                   .getMethod("currentApplication")
                   .invoke(null) as Application
           } catch (e: IllegalAccessException) {
               e.printStackTrace()
           } catch (e: InvocationTargetException) {
               e.printStackTrace()
           } catch (e: NoSuchMethodException) {
               e.printStackTrace()
           } catch (e: ClassNotFoundException) {
               e.printStackTrace()
           }
           if (app == null) {
               throw NullPointerException("AndUtil application is null")
           }
           app!!
       }
    }


    val appContext: Context by lazy { application.applicationContext }

    class Config{
        companion object{
            fun build() = Config()
        }
        /**
         * 设置默认的SharePreference表名
         */
        fun setDefaultSpFile(fileName: String) = this.apply { setSpDefaultFile(fileName) }
    }
}