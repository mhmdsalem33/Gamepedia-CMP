package org.gamepdia.coreDatabase

actual fun getPlatformDriverFactory(): DatabaseDriverFactory {
    return DesktopDatabaseDriverFactory()
}
