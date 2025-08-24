package com.dcharcha.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.dcharcha.domain.model.Item

@Composable
fun HomeRoute(
    vm: HomeViewModel = hiltViewModel(),
    onItemClick: (Item) -> Unit
) {
    val query by vm.query.collectAsState()
    val pagingItems = vm.pagingFlow.collectAsLazyPagingItems()

    HomeScreen(
        query = query,
        onQueryChange = vm::onQueryChange,
        items = pagingItems,
        onRetry = { pagingItems.retry() },
        onRefresh = { pagingItems.refresh() },
        onItemClick = onItemClick
    )
}
