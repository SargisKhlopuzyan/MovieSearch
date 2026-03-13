package com.sargis.ui

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import com.sargis.domain.usecase.SearchUseCase
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update

// this will expose viewmodel in form of state object in iOS side
class SearchViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(viewModelScope, SearchUiState())

    @NativeCoroutinesState
    val uiState = _uiState.asStateFlow()

    private val _query = MutableStateFlow(viewModelScope, "")

    @NativeCoroutinesState
    val query = _query.asStateFlow()

    fun updateQuery(q: String) {
        _query.update { q }
    }

    init {
        viewModelScope.launch {
            _query.filter {
                it.isNotEmpty()
            }.debounce(500)
                .distinctUntilChanged().collectLatest { query ->
                    _uiState.update {
                        SearchUiState(isLoading = true)
                    }
                    searchUseCase.execute(query)
                        .onSuccess { data ->
                            _uiState.update {
                                SearchUiState(isLoading = false, data = data)
                            }
                        }.onFailure {
                            _uiState.update {
                                SearchUiState(isLoading = false, error = it.error)
                            }
                        }
                }
        }
    }

}