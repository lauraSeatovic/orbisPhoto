package com.example.orbisphoto.repository

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GroupListResponse(
    @SerialName("groups")
    val groups: List<GroupResponse>
)
