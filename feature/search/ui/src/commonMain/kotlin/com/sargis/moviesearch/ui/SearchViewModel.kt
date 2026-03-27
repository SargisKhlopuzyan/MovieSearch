package com.sargis.moviesearch.ui

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import com.sargis.moviesearch.domain.usecase.SearchUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update

// this will expose viewmodel in form of state object in iOS side
@OptIn(FlowPreview::class)
class SearchViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(viewModelScope, SearchUiState())

    @NativeCoroutinesState
    val uiState = _uiState.asStateFlow()

    private val _query = MutableStateFlow(viewModelScope, "")

    @NativeCoroutinesState
    val query = _query.asStateFlow()

    init {
        viewModelScope.launch {
            _query.debounce(500)
                .distinctUntilChanged().collectLatest { query ->
                    _uiState.update {
                        SearchUiState(isLoading = true)
                    }
                    searchUseCase.execute(query)
                        .onSuccess { movies ->
                            _uiState.update {
                                SearchUiState(isLoading = false, searchedMovies = movies)
                            }
                        }.onFailure {
                            _uiState.update {
                                SearchUiState(isLoading = false, error = it.error)
                            }
                        }
                }
        }
    }

    fun onAction(action: MoviesAction) {
        when (action) {
            is MoviesAction.OnSearchQueryChange -> _query.update { action.query }
            is MoviesAction.OnTabSelected -> _uiState.update {
                it.copy(selectedTabIndex = action.tabIndex)
            }
            is MoviesAction.OnMovieClick -> {}
        }
    }
}