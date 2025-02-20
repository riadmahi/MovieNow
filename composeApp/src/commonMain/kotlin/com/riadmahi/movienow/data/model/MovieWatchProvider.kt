package com.riadmahi.movienow.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class MovieWatchProviders(
    val id: Int,
    val results: Map<String, CountryWatchProviders>
)

@Serializable
data class CountryWatchProviders(
    val link: String,
    @SerialName("flatrate")
    val flatRate: List<WatchProvider>? = null
)

@Serializable
data class WatchProvider(
    @SerialName("logo_path")
    val logoPath: String?,
    @SerialName("provider_id")
    val providerId: Int,
    @SerialName("provider_name")
    val providerName: String,
    @SerialName("display_priority")
    val displayPriority: Int
)