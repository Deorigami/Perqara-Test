package com.ardinata.test.perqara

import android.app.Application
import com.ardinata.test.perqara.core.util.LocaleUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        LocaleUtil.init(this)
    }
}