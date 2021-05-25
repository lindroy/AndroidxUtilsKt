# AndroidxUtilsKt

[![](https://www.jitpack.io/v/lindroy/AndroidxUtilsKt.svg)](https://www.jitpack.io/#lindroy/AndroidxUtilsKt)

使用`kotlin`编写的Android工具类，兼容`Androidx`，主要采用扩展函数的方式。目前还在不断补充中。

本说明文档遵循如下的规则：

1. 根据工具类文件名的英文字母顺序排序。
2. 接收类一栏为“/”的表示该方法或属性为全局成员，可在任意地方调用；接收类有具体类名（如Any、String?等）的表示采用扩展成员的写法；放在包“extension”中；
3. 接收类一栏为“—”的表示采用静态类的写法，需要使用类名的形式调用里面的函数或属性，放在包“statics”中；
4. 成员包含函数和属性，函数名称后面需要加“()”，属性则不用；
5. “JvmName”表示该成员在Java中调用的名称；
6. 常量另外写一个表格。

## 配置方法

### 1、在工程gradle中添加：
```
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
```
### 2、添加如下依赖：

```
dependencies {
     implementation 'com.github.lindroy:AndroidxUtilsKt:0.0.1-beta'
}
```

### 3、在Application中初始化（非必需）

`AndroidxUtilsKt`已经采取反射的方式获取全局的`Application`了，但如果获取失败的话，可以在应用的`Application`中传入`Application`实例，同时也可以调用一些配置方法：

```kotlin
        AndUtil.init(this)
            .setDefaultSpFile()     //设置SharePreferences的默认表名，默认为“sp_util”  
```
## 工具类文档

### ActivityUtil：Activity工具类

| 成员名称  | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :----------: |
| launchActivity()   | 启动一个Activity，可传递Intent  |  Context  |  启动的Activity作为泛型传入  |
| launchActivity()   |启动一个Activity，并传递参数  | Context | 启动的Activity作为泛型传入 |
| launchActivityForResult() | 启动一个Activity但不传递数据，并获得回调 | FragmentActivity | 启动的Activity作为泛型传入 |
| launchActivityForResult() | 启动一个Activity但不传递数据，并获得回调 | Fragment | 启动的Activity作为泛型传入 |
| launchActivityForResult() | 启动一个Activity，传入参数并获得回调 | FragmentActivity | 启动的Activity作为泛型传入 |
| launchActivityForResult() | 启动一个Activity，传入参数并获得回调 | Fragment | 启动的Activity作为泛型传入 |
| extraString() | 获取String类型参数 | FragmentActivity |  |
| extraBoolean() | 获取Boolean类型参数 | FragmentActivity |  |
| extraInt() | 获取Int类型参数 | FragmentActivity |  |
| extraLong() | 获取Long类型参数 | FragmentActivity |  |
| extraDouble() | 获取Double类型参数 | FragmentActivity |  |
| extraFloat() | 获取Float类型参数 | FragmentActivity |  |
| extraParcelable() | 获取Parcelable类型参数 | FragmentActivity |  |
| extraSerializable() | 获取Serializable类型参数 | FragmentActivity |  |
| extraParam() | 从Intent中获取参数 | FragmentActivity | |
| extraParam() | 从Intent中获取参数 | Fragment | |

------------

- `launchActivityForResult()`方法可以在启动一个Activity时传值，并在其销毁时获得传回来的数据，用于替代`onActivityReuslt`，示例用法如下：

```kotlin
launchActivityForResult<MainActivity> { resultCode, data -> 
    if (resultCode == RESULT_OK && data != null){
    }
}
```

- `extraXXX()`方法用于从Intent中取值，内部采用了延迟加载，只要第一次使用到这个常量时才会赋值：

```Kotlin
private val num by extraInt("num",0)
```

### AppManager：App管理器

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| addActivity()   | Activity入栈  |  —  |   /  |
| removeActivity()   | Activity出栈  |  —  |  /   |
| finishActivity()   | 关闭一个Activity  |  — |  /  |
| currentActivity()   |  获取当前栈顶Activity |  — |  如果栈内元素为空，则返回null  |
| finishAllActivity()   |  清理栈中所有的Activity | — |    / |
| exitApp()  |  退出应用程序 | — |   /  |

------------

### AppUtil：应用信息工具类
| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| getAppVersionName()   | 获取应用版本名称，默认为本应用  | Context  | /  |
| getAppVersionCode()   | 获取应用版本号，默认为本应用  | Context  |  / |
| getAppSize()   | 获取应用大小，默认为本应用  | Context  | 返回值单位为b  |
| getAppIcon()   |获取应用图标，默认为本应用| Context  | 失败时返回null  |

------------

### BarUtil：栏工具类

#### 状态栏（StatusBar）
文件名为`StatusBarUtil`，在Java中调用时使用`BarUtil`即可。

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| statusBarHeight  | 获取状态栏高度  | / | 单位为px |
| setStatusBarColor | 设置纯颜色状态栏  | Activity  | 参数为ColorInt |
| setStatusBarColorRes()  | 设置纯颜色状态栏 | Activity | 参数为ColorRes  |
| setTransParentStatusBar() | 设置透明状态栏 | Activity | 在界面创建时调用才能生效 |
| setGradientStatusBar() | 设置渐变色状态栏 | Activity | 参数为Drawable或DrawableRes |
| setStatusBarDarkMode() | 设置状态栏白色字体图标(深色模式) | Activity | / |
| setStatusBarLightMode() | 设置状态栏黑色字体图标(浅色模式) | Activity | / |

Demo 中已经给出了沉浸式状态栏的几种适配方法义工参考。

#### 导航栏（NavigationBar）

文件名为`NavigationBarUtil`，在Java中调用时使用`BarUtil`即可。

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| hasNavBar | 手机是否有虚拟导航栏 | / | / |
| isNavBarShowed | 当前虚拟导航栏是否显示 | Activity/Window | / |
| isNavBarHidden | 当前虚拟导航栏是否隐藏 | Activity/Window | / |
| navBarHeight | 获取虚拟导航栏的高度 | / | 必须在布局绘制完成之后调用 |
| navBarColor | 获取/设置虚拟导航栏颜色 | Activity/Window | 颜色值为ColorInt |
| setNavBarColorRes() | 获取/设置导航栏颜色 | Activity/Window | 参数为资源ID  |

------------

### BitmapUtil：Bitmap相关工具类
| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| createBitmapSafely() | 获取应用版本名称，默认为本应用 | /  | / |
| viewToBitmap() | 将View转换为Bitmap  | / |  / |

------------

### BrightnessUtil：屏幕亮度工具类
设置系统屏幕亮度时需要动态申请系统设置权限：

```xml
    <uses-permission android:name="android.permission.WRITE_SETTINGS" />
```

```kotlin
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //如果当前平台版本大于23平台
            if (!Settings.System.canWrite(mContext)) {
                //未获取权限
                val intent = with(Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)) {
                    data = Uri.parse("package:$packageName")
                    this
                }
                startActivityForResult(intent, 100)
            } else {
               //已经获得了权限
            }
        }
```

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| isAutoBrightness  | 是否开启了自动亮度 | / | / |
| setAutoBrightness()  | 设置是否开启自动亮度  | / | 设置成功返回true  |
| systemBrightness |  获取/设置系统屏幕宽度 | / | 亮度范围为0~255 |
| windowBrightness | 获取/设置当前窗口亮度  | Activity | 亮度范围为0~1.0，1为最亮，默认为-1 |

------------



### DensityUtil：像素单位转换工具类

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| dp2px() | dp转px | / | / |
| px2dp() | px转dp | / | / |
| sp2px() | sp转px | / | / |
| px2sp() | px转sp | / | / |

------------

### IntentUtil：意图工具类

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| launchSystemSetting()   | 打开系统设置界面  | — | /  |
| launchWifiSetting()   | 打开wifi设置界面  | — | / |
| launchDialPage()   | 打开拨号面板  | — | /  |
| callPhone()   | 直接拨打电话  | — | 动态权限  |
| launchBrowse()   | 调用浏览器并打开一个网页  | — | /  |
| launchCamera()   | 启动系统相机  | — | 动态权限  |
| sendSMS()   | 发送短信  | — | 动态权限  |

------------

### KeyboardUtil：软键盘工具类

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| showKeyboard()  | 打开软键盘  | View  | /  |
| hideKeyboard()  | 关闭软键盘  | View  | /  |
| toggleKeyboard()  | 根据当前软键盘的状态做取反操作  | View  | /  |

#### 监听软键盘的状态
监听软键盘的显示和隐藏可以使用`KeyboardStatusWatcher`类，使用方法见下面代码。但要注意：软键盘的状态和高度必须在设置了监听事件之后才能获取，且
```kotlin
        val keyboardStatus = KeyboardStatusWatcher(llRoot)
        //监听软键盘状态
        keyboardStatus.addKeyboardStatusWatcher { isShowed, keyboardHeight ->
            val status = if (isShowed) "软键盘显示，高度为${px2dp(keyboardStatus.keyboardHeight)}" else "软键盘收起"
            shortToast(status)
        }
        //软键盘高度
        keyboardStatus.keyboardHeight
        //软键盘是否显示
        keyboardStatus.isKeyboardShowed
        //软键盘是否隐藏
        keyboardStatus.isKeyboardHidden
```

------------

### NetworkUtil：网络状态工具类
#### 常量

| 常量名  | 值 | 意义 |
| ------------ |  :------------: | ------------ |
| NETWORK_NONE | -1 | 没有网络 |
| NETWORK_UNKNOWN | -2 | 未知网络 |
| NETWORK_MOBILE | 1  | 移动网络 |
| NETWORK_WIFI | 2  | 无线网络 |

#### 成员名称

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| networkState | 获取当前的网络状态  | /  |  / |
| isWifi  | 是否是wifi  | /  | /  |
| isMobileNet  | 是否是移动网络  | /  | / |
| isNetworkConnect  | 网络是否连接  | /  | / |

------------

### ResourceUtil：资源工具类
在`Java`中使用时，工具类名为`ResUtil`。

| 成员名称 | JvmName | 作用  | 接收类  | 备注  |
| ------------ | ------------ | ------------ | :------------: | :------------: |
| getResColor()   | getColor() |获取颜色  | /  | /  |
| getResDrawable() | getDrawable() | 获取图片资源  | / | 返回值可以为null  |
| getResString() | getString()  | 获取字符资源 | / | / |
| getResDimenPx() | getDimenPx() |获取dimens资源 | / | / |
| getResDimenDp() | getDimenDp() |获取dimens中单位为dp的资源 | / | / |
| getResDimenSp() | getDimenSp() | 获取dimens中单位为Sp的资源 | /  | / |
| getResStringArray() | getStringArray() | 获取String数组 | / | / |
| getResIntArray() | getIntArray() | 获取Int数组 | / | / |
| getResTextArray() | getTextArray() | 获取Char数组 | / | / |

------------

### ScreenUtil：屏幕相关工具类

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| screenWidth  | 获取屏幕宽度  | / | /  |
| screenHeight | 获取屏幕高度  | / | /  |
| screenDensity | 获取屏幕密度  | / | /  |
| screenDPI | 获取屏幕DPI  | Any  | / |
| screenOrientation  | 获取屏幕方向  | / | /  |
| isLandscape | 是否是横屏  | / | / |
| isPortrait | 是否是竖屏  | / | /  |
| setScreenLandscape() | 设置横屏  | Activity  | /  |
| setScreenPortrait() | 设置竖屏  | Activity  | /  |
| toggleScreenOrientation() | 横竖屏切换  | Activity  | /  |
| lockScreenOrientation() | 锁定屏幕方向  | Activity  | /  |
| unlockScreenOrientation() | 取消锁定屏幕方向  | Activity | /  |
| isFullScreen | 判断和设置是否全屏 | Activity | 赋值为true设置成全屏 |
| setFullScreen() | 设置全屏 | Activity | / |
| setNonFullScreen() | 设置非全屏 | Activity | / |
| isScreenOn | 屏幕是否亮屏 | / | / |
| isScreenOff | 屏幕是否熄灭 | / | / |
| isScreenLocked | 屏幕是否锁屏 | / | / |
| isScreenUnlocked | 屏幕是否解锁 | / | / |
| isKeepScreenOn | 判断和设置是否保持屏幕常亮 | Activity | 只作用于当前窗口 |
| setKeepScreenOn() | 保持屏幕常亮 | Activity | 只作用于当前窗口 |
| setNonKeepScreenOn | 取消保持屏幕常亮 | Activity | 只作用于当前窗口 |
| getScreenAutoLockTime() | 获取自动锁屏时间 | / | 需要WRITE_SETTINGS权限 |
| setScreenAutoLockTime() | 设置自动锁屏时间 | / | 需要WRITE_SETTINGS权限 |
| setScreenAutoLockNever() | 设置永不自动锁屏 | / | 需要WRITE_SETTINGS权限 |

------------
### SDCardUtil：SD卡工具类
| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| isSDCardMounted | 判断SD卡是否已挂载 | / | / |
| sdCardPath | 获取SD卡路径 | / | 失败时返回空字符 |
| sdCardTotalSize | 获取SD卡的总大小 | / | 失败时返回-1 |
| sdCardAvailableSize | 获取SD卡可用空间大小 | / | 失败时返回-1 |


------------

### SpUtil：SharedPreferences工具类

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| setSpDefaultFile()  |  设置默认的表名 | / | 调用AndUtil.init()方法配置 |
| putSpString()  |  存储字符串 | /  | /  |
| getSpString()  | 取出存储的字符串 | / | /  |
| putSpBoolean() | 存储布尔值 | / | /  |
| getSpBoolean() | 取出存储的布尔值 | / |  / |
| putSpInt() | 存储Int值 | / | /  |
| getSpInt() | 取出存储的Int值 | / | / |
| putSpLong() | 存储Long值 | / | /  |
| getSpLong() | 取出存储的Long值 | / | / |
| putSpFloat() | 存储Float值 | / | /  |
| getSpFloat() | 取出存储的Float值 | / | / |
| putSpStrSet() | 存储StringSet | / | / |
| getSpStrSet() | 取出存储的StringSet | / |  /  |
| putSp() | 保存数据 | / | 数据类型由传入的值确定  |
| getSp() |取出数据 | / | 数据类型由传入的默认值确定  |
| deleteSpKey() | 删除某条数据 | / | /  |
| clearSp() | 清除SharedPreferences的数据 | 不输入表名则清除默认表中的数据 | /  |

------------

### ToastUtil：Toast工具类
已去除小米手机自带的应用名称。

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| shortToast()  | 显示短Toast  | / | /  |
| longToast()  | 显示长Toast  | / |  / |

------------

### [ViewUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/extension/ViewUtil.kt "ViewUtil")：View工具类
| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| inflate() | 填充一个View | / | / |
| isVisible  | 当前View是否可见  | View | /  |
| isInvisible | 当前View是否不可见  | View | / |
| isGone | 当前View是否隐藏  | View | /  |
| setGone()  | 将View设置为隐藏  | View | /  |
| setVisible()  | 将View设置为可见  | View |  / |
| setInVisible()  | 将View设置为不可见  | View |  / |
| setWidth()  | 设置View的宽度  | View |  / |
| setHeight()  | 设置View的高度  | View |  / |
| setWidthAndHeight()  | 设置View的宽度和高度  | — |  / |
| setNewPadding() | 设置View的padding  | View |  / |
| viewHeight  | 获取View的高度  | View | 如果是“math_parent”属性则无法获取，值为0。 |
| viewWidth  | 获取View的宽度  | View | 同上 |
| textString | 获取TextView的String内容  | TextView | / |
| textLength | 获取TextView的String内容长度 | TextView | /  |
| isTextEmpty | 判断TextView的内容是否为空 | TextView |  / |
| isTextNullOrEmpty | 判断TextView的内容是否为null或空 | TextView | / |
| isTextNotEmpty | 判断TextView的内容是否为非空 | TextView | / |
| isTextBlank | 判断TextView的内容是否为空白 | TextView | / |
| isTextNullOrBlank | 判断TextView的内容是否为null或空白 | TextView | / |
| isTextNotBlank | 判断TextView的内容是否为非空白 | TextView | / |

## 感谢
1. [Anko](https://github.com/Kotlin/anko "Anko")
2. [AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode "AndroidUtilCode")
3. [Qmui](https://qmuiteam.com/android "Qmui")

 
