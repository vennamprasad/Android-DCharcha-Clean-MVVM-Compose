package com.dcharcha.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dcharcha.domain.model.Item
import com.dcharcha.domain.usecase.GetPagedItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPagedItems: GetPagedItemsUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    val pagingFlow: kotlinx.coroutines.flow.Flow<PagingData<Item>> =
        _query.debounce(300)
            .flatMapLatest { q -> getPagedItems(q) }
            .cachedIn(viewModelScope)

    fun onQueryChange(new: String) = _query.update { new }
}
