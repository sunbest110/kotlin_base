package com.sunmeng.base.di

import com.sunmeng.base.api.BaseService
import com.sunmeng.base.common.Constants
import com.sunmeng.base.net.RetrofitManager
import org.koin.dsl.module
import retrofit2.Retrofit

//viewModel
val viewModelModule = module {
    //factory { } 每次都会产生新的实例
    //single  { } 单例
    //get() 获取已经上面已经注解过的
}

//请求
val repoModule = module {
    single<Retrofit> { RetrofitManager.getRetrofit(Constants.HOST_API) }
    single<BaseService> { get<Retrofit>().create(BaseService::class.java) }
}

//当需要构建你的ViewModel对象的时候，就会在这个容器里进行检索
val appModule = listOf(viewModelModule, repoModule)