package org.gamepdia.coreDatabase

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

class DesktopDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        // هنا بتخلي الـ DB يتخزن كملف عادي
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:AppDatabase.db")
        // إنشاء الجداول لأول مرة
        AppDatabase.Schema.create(driver)
        return driver
    }
}
