package com.riadmahi.movienow.utils

actual fun getDefaultLanguage(): String {
    return java.util.Locale.getDefault().toLanguageTag()
}