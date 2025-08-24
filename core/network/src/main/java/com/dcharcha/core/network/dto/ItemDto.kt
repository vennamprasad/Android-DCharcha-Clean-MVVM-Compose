package com.dcharcha.core.network.dto

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ItemDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
