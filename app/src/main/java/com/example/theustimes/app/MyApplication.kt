package com.example.theustimes.app

import android.app.Application
import com.example.theustimes.di.component.ApplicationComponent
import com.example.theustimes.di.component.DaggerApplicationComponent
import com.example.theustimes.di.modules.ApplicationModule

class MyApplication: Application() {
    private lateinit var mAppComponent: ApplicationComponent

    init {
        instance_ = this
    }

    companion object {
        lateinit var instance_: MyApplication

        fun getInstance() = instance_
    }

    override fun onCreate() {
        super.onCreate()

        mAppComponent =
            DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule())
                .build()
    }

    fun getAppComponent() = mAppComponent
}