package com.dcharcha.domain.usecase

import com.dcharcha.domain.model.HomeItem

class GetHomeItemsUseCase {
    operator fun invoke(): List<HomeItem> = emptyList()
}

class RefreshHomeUseCase {
    suspend operator fun invoke() {}
}
