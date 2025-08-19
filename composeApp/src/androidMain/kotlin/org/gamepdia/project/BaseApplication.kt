package org.gamepdia.project

import android.app.Application
import org.gamepdia.coreDatabase.initDatabaseModule
import org.gamepdia.project.di.initKoin

class BaseApplication : Application()  {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        initDatabaseModule(this)
    }



}