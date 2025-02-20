package com.riadmahi.movienow.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class MovieCredits(
    val id: Int,
    val cast: List<CastMember>,
    val crew: List<CrewMember>
)

@Serializable
data class CastMember(
    @SerialName("cast_id")
    val castId: Int,
    val character: String,
    @SerialName("credit_id")
    val creditId: String,
    val gender: Int?,
    val id: Int,
    val name: String,
    val order: Int,
    @SerialName("profile_path")
    val profilePath: String?
)

@Serializable
data class CrewMember(
    @SerialName("credit_id")
    val creditId: String,
    val department: String,
    val gender: Int?,
    val id: Int,
    val job: String,
    val name: String,
    @SerialName("profile_path")
    val profilePath: String?
)