package com.riadmahi.movienow

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform