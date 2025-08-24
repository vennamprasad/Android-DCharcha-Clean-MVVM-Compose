package com.dcharcha.data.remote

import com.dcharcha.core.network.dto.ItemDto

interface ItemRemoteDataSource {
    suspend fun getItems(page: Int, pageSize: Int): List<ItemDto>
    suspend fun getItem(id: Long): ItemDto
}