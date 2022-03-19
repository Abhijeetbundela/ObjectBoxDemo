package com.oss.demo.database

import android.content.Context
import com.oss.demo.model.MyObjectBox
import com.oss.demo.model.User
import com.oss.demo.model.User_
import io.objectbox.BoxStore
import io.objectbox.android.ObjectBoxLiveData

object ObjectBox {

    lateinit var boxStore: BoxStore
        private set

    fun init(context: Context) {
        boxStore = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()
    }

}