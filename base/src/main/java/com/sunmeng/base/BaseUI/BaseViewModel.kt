package com.sunmeng.base.BaseUI

import androidx.lifecycle.ViewModel

//在kotlin中它所有的类默认都是final的，那么就意味着不能被继承，而且在类中所有的方法也是默认是final的,
// open的作用就是让其他可以继承
open class BaseViewModel : ViewModel() {
    override fun onCleared() {
        super.onCleared()
        print("onCleared")
    }
}