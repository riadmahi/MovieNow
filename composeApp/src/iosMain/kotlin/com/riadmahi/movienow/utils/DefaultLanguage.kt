package com.riadmahi.movienow.utils

import platform.Foundation.NSLocale
import platform.Foundation.preferredLanguages

actual fun getDefaultLanguage(): String {
    val languages = NSLocale.preferredLanguages
    return languages.first() as? String ?: "en-US"
}