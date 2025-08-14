package org.gamepdia.project

import android.app.Application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.gamepdia.project.di.initKoin

class BaseApplication : Application()  {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        initLogger()
    }


    fun initLogger() {
        Napier.base(DebugAntilog())
    }
}