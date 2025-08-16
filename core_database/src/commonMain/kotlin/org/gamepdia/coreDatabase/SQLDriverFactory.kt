package org.gamepdia.coreDatabase

import app.cash.sqldelight.db.SqlDriver


interface DatabaseDriverFactory{
    fun createDriver() : SqlDriver
}

