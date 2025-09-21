package com.dcharcha.data.mapper

import com.dcharcha.core.database.entity.ItemEntity
import com.dcharcha.core.network.dto.ItemDto
import com.dcharcha.domain.model.Item

fun ItemDto.toEntity() = ItemEntity(
    id = this.id.toLong(),
    title = this.title,
    subtitle = this.body
)

fun ItemEntity.toDomain() = Item(
    id = this.id,
    title = this.title,
    subtitle = this.subtitle
)

