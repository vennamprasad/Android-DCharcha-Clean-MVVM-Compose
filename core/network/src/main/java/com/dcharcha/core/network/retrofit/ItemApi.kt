package com.dcharcha.core.network.retrofit

import com.dcharcha.core.network.dto.ItemDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemApi {
    @GET("posts")
    suspend fun getItems(
        @Query("_page") page: Int,
        @Query("_limit") pageSize: Int
    ): List<ItemDto>

    @GET("posts")
    suspend fun getItem(@Query("id") id: Int): ItemDto
}
