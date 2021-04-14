package com.sunmeng.base.di

import org.koin.dsl.module

//viewModel
val viewModelModule = module {
    //factory { } 每次都会产生新的实例
    //single  { } 单例
    //get() 获取已经上面已经注解过的
}
//请求
val repoModule = module {

}

//当需要构建你的ViewModel对象的时候，就会在这个容器里进行检索
val appModule = listOf(viewModelModule, repoModule)