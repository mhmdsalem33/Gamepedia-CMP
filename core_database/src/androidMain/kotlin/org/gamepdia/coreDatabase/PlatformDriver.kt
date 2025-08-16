package org.gamepdia.coreDatabase


import android.content.Context



lateinit var appContext: Context

fun initDatabaseModule(context: Context) {
    appContext = context
}

actual fun getPlatformDriverFactory(): DatabaseDriverFactory {
    return AndroidDatabaseDriverFactory(appContext)
}
