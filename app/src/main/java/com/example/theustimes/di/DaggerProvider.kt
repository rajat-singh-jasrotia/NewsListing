package com.example.theustimes.di

import com.example.theustimes.app.MyApplication
import com.example.theustimes.di.component.AppComponent
import com.example.theustimes.di.component.DaggerAppComponent
import com.example.theustimes.di.modules.ApplicationModule

class DaggerProvider {

    companion object {
        private var appComponent: AppComponent? = null

        fun initComponent(application: MyApplication) {
            appComponent = DaggerAppComponent.builder()
                .applicationModule(ApplicationModule(application))
                .build()
        }

        fun getAppComponent(): AppComponent? {
            return appComponent
        }
    }
}