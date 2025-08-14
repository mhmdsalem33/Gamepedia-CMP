package org.gamepdia

import io.github.aakira.napier.Napier

fun logError(message: String, throwable: Throwable? = null) {
    Napier.e(message, throwable)
}