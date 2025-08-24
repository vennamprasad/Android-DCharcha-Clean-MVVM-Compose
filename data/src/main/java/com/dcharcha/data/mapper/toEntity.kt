package com.dcharcha.data.mapper

import com.dcharcha.core.database.entity.ItemEntity
import com.dcharcha.core.network.dto.ItemDto
import com.dcharcha.domain.model.Item

fun ItemDto.toEntity() = ItemEntity(id, title, subtitle)
fun ItemEntity.toDomain() = Item(id, title, subtitle)
