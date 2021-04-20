package com.sunmeng.base.net

import com.google.gson.GsonBuilder
import com.sunmeng.base.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object RetrofitManager {
    //lateinit var context: Context//懒加载
    private lateinit var mRetrofit: Retrofit
    private val retrofitMap = HashMap<String, Retrofit>()
    private val clientMap = HashMap<String, OkHttpClient>()
    private val DEFAULT_TIMEOUT: Long = 20
    private val CONNECT_TIMEOUT: Long = 20
    private val READ_TIMEOUT: Long = 20
    private val WRITE_TIMEOUT: Long = 20
    fun getRetrofit(baseUrl: String): Retrofit {
        if (empty(baseUrl)) {
            throw IllegalStateException("baseUrl can not be null")
        }
        if (retrofitMap[baseUrl] != null) {//直接复用
            return retrofitMap[baseUrl]!!
        }
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()

        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getClient(baseUrl))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))

        val retrofit = builder.build()
        retrofitMap.put(baseUrl, retrofit)

        return retrofit
    }

    private fun getClient(baseUrl: String): OkHttpClient {
        if (empty(baseUrl)) {
            throw IllegalStateException("baseUrl can not be null")
        }
        if (clientMap[baseUrl] != null) {
            return clientMap[baseUrl]!!
        }


        val builder = OkHttpClient.Builder()

        builder.connectTimeout(
            CONNECT_TIMEOUT, TimeUnit.SECONDS
        )
        builder.readTimeout(
            READ_TIMEOUT, TimeUnit.SECONDS
        )

        builder.writeTimeout(
            WRITE_TIMEOUT, TimeUnit.SECONDS
        )
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }

        val client = builder.build()
        clientMap.put(baseUrl, client)

        return client
    }

    private fun empty(baseUrl: String?): Boolean {
        return baseUrl == null || baseUrl.isEmpty()
    }

}