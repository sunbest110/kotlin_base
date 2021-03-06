package com.sunmeng.base.BaseUI

import android.app.Application
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.alibaba.android.arouter.launcher.ARouter
import com.sunmeng.base.BuildConfig
import com.sunmeng.base.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin


class BaseApplication : Application() {
    companion object {
        /**
         * 相当于Java的static
         */
        //var TAG = "CHANGE";
        /**
         * 相当于Java的 public final static
         */
        // const val TAG = "PUBLIC"

        /**
         * 相当于Java的 private final static
         */
        val TAG = "BaseApplication"
    }

    override fun onCreate() {
        super.onCreate()
        //Arouter初始化
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
        //lifecycle 生命周期
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationLifecycleObserver())
        //koin 注解
       // startKoin(this, appModule, logger = AndroidLogger(showDebug = BuildConfig.DEBUG))
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(appModule)
        }
    }

    /**
     * Application生命周期观察，提供整个应用进程的生命周期
     *
     * Lifecycle.Event.ON_CREATE只会分发一次，Lifecycle.Event.ON_DESTROY不会被分发。
     *
     * 第一个Activity进入时，ProcessLifecycleOwner将分派Lifecycle.Event.ON_START, Lifecycle.Event.ON_RESUME。
     * 而Lifecycle.Event.ON_PAUSE, Lifecycle.Event.ON_STOP，将在最后一个Activit退出后后延迟分发。如果由于配置更改而销毁并重新创建活动，则此延迟足以保证ProcessLifecycleOwner不会发送任何事件。
     *
     * 作用：监听应用程序进入前台或后台
     */
    class ApplicationLifecycleObserver : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onAppForeground() {
            Log.e(TAG, "应用在前台")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onAppBackground() {
            Log.e(TAG, "应用在前台")
        }
    }
}