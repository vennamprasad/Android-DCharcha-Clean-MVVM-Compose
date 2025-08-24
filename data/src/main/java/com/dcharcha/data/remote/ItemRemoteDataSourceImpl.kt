package com.dcharcha.data.remote

import com.dcharcha.core.network.dto.ItemDto
import com.dcharcha.core.network.retrofit.ItemApi
import javax.inject.Inject

class ItemRemoteDataSourceImpl @Inject constructor(
    private val api: ItemApi
) : ItemRemoteDataSource {
    override suspend fun getItems(page: Int, pageSize: Int): List<ItemDto> =
        api.getItems(page, pageSize)

    override suspend fun getItem(id: Long): ItemDto =
        api.getItem(id.toInt())
}