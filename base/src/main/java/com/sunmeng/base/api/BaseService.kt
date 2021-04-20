package com.sunmeng.base.api

import io.reactivex.Single
import retrofit2.http.GET

//例子
interface BaseService {

    @GET("hhh")
    fun getSlider(): Single<String>

}
