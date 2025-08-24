package com.dcharcha.domain.usecase

import androidx.paging.PagingData
import com.dcharcha.domain.model.Item
import com.dcharcha.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagedItemsUseCase @Inject constructor(
    private val repo: ItemRepository
) {
    operator fun invoke(query: String): Flow<PagingData<Item>> = repo.pagedItems(query)
}
