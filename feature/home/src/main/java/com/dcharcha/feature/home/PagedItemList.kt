import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.dcharcha.domain.model.Item
import com.dcharcha.feature.home.AppendError
import com.dcharcha.feature.home.ItemCard
import com.dcharcha.feature.home.ItemPlaceholderCard

@Composable
fun PagedItemList(
    items: LazyPagingItems<Item>,
    listState: LazyListState,
    onItemClick: (Item) -> Unit
) {
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            count = items.itemCount
        ) { index ->
            val item = items[index]
            if (item != null) {
                ItemCard(item = item, onClick = { onItemClick(item) })
            } else {
                ItemPlaceholderCard()
            }
        }

        when (val append = items.loadState.append) {
            is LoadState.Loading -> item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is LoadState.Error -> item {
                AppendError(
                    message = append.error.message ?: "Could not load more.",
                    onRetry = { items.retry() }
                )
            }

            else -> Unit
        }
    }
}
