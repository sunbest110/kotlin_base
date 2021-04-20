package com.sunmeng.base.net

import android.content.Context
import retrofit2.Retrofit
import java.util.HashMap

//网络管理类
class RetrofitClientManager {
    lateinit var context: Context//懒加载
    private lateinit var mRetrofit: Retrofit
    private val retrofitMap = HashMap<String, Retrofit>()
    private val DEFAULT_TIMEOUT:Long=20

    companion object {//伴生对象
        val DEFAULT_TIMEOUT = 20
    }
}