package com.dcharcha.feature.home

import PagedItemList
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.dcharcha.domain.model.Item

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    query: String,
    onQueryChange: (String) -> Unit,
    items: LazyPagingItems<Item>,
    onRetry: () -> Unit,
    onRefresh: () -> Unit,
    onItemClick: (Item) -> Unit
) {
    val listState = rememberSaveable(saver = LazyListState.Saver) {
        LazyListState(firstVisibleItemIndex = 0, firstVisibleItemScrollOffset = 0)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Items (Paging)") },
                actions = {
                    IconButton(onClick = onRefresh) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SearchBar(
                value = query,
                onValueChange = onQueryChange,
                onDone = onRefresh,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            Box(Modifier.fillMaxSize()) {
                PagedItemList(
                    items = items,
                    listState = listState,
                    onItemClick = onItemClick
                )

                // Full‑screen states (overlay)
                when (val state = items.loadState.refresh) {
                    is LoadState.Loading -> FullscreenLoading()
                    is LoadState.Error -> FullscreenError(
                        message = state.error.message ?: "Failed to load.",
                        onRetry = onRetry
                    )

                    is LoadState.NotLoading -> {
                        if (items.itemCount == 0) {
                            EmptyState(
                                title = "No results",
                                subtitle = if (query.isBlank()) "Try adding a query." else "No items for “$query”.",
                                onRefresh = onRefresh
                            )
                        }
                    }
                }
            }
        }
    }
}