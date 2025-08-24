package com.dcharcha.domain.repository
import androidx.paging.PagingData
import com.dcharcha.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun pagedItems(query: String): Flow<PagingData<Item>>
}
