package com.sargis.moviesearch.feature.search.ui

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.coroutineScope
import com.rickclephas.kmp.observableviewmodel.launch
import com.sargis.moviesearch.core.domain.onError
import com.sargis.moviesearch.core.domain.onSuccess
import com.sargis.moviesearch.core.ui.UiText
import com.sargis.moviesearch.feature.search.domain.usecase.ObserveFavoriteMoviesUseCase
import com.sargis.moviesearch.feature.search.domain.usecase.SearchUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

// this will expose viewmodel in form of state object in iOS side
@OptIn(FlowPreview::class)
class SearchViewModel(
    private val searchUseCase: SearchUseCase,
    private val observeFavoriteMoviesUseCase: ObserveFavoriteMoviesUseCase
) : ViewModel() {

    private var searchJob: Job? = null
    private var observeFavoriteJob: Job? = null

    private val _uiState = MutableStateFlow(viewModelScope, SearchUiState())

    @NativeCoroutinesState
    val uiState = _uiState.onStart {
        observeSearchQuery()
        observeFavoriteMovies()
    }.stateIn(
        viewModelScope.coroutineScope,
        SharingStarted.WhileSubscribed(5_000L),
        _uiState.value
    )

    private val _query = MutableStateFlow(viewModelScope, "")

    @NativeCoroutinesState
    val query = _query.asStateFlow()

    fun onAction(action: MoviesAction) {
        when (action) {
            is MoviesAction.OnSearchQueryChange -> _query.update { action.query }
            is MoviesAction.OnTabSelected -> _uiState.update {
                it.copy(selectedTabIndex = action.tabIndex)
            }
            is MoviesAction.OnMovieClick -> {}
        }
    }

    private fun observeSearchQuery() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _query.debounce(500)
                .distinctUntilChanged().collectLatest { query ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = true
                        )
//                        SearchUiState(isLoading = true)
                    }
                    searchUseCase.execute(query)
                        .onSuccess { movies ->
                            _uiState.update { currentState ->
                                currentState.copy(
                                    isLoading = false,
                                    searchedMovies = movies
                                )
//                                SearchUiState(isLoading = false, searchedMovies = movies)
                            }
                        }.onError { dataError ->
                            _uiState.update { currentState ->
                                currentState.copy(
                                    isLoading = false,
                                    error = UiText.DynamicString(dataError.toString())                                )
//                                SearchUiState(isLoading = false, error = it.error)
                            }
                        }
                }
        }
    }

    private fun observeFavoriteMovies() {
        observeFavoriteJob?.cancel()
        observeFavoriteJob = viewModelScope.launch {
            observeFavoriteMoviesUseCase.execute().collect {
                _uiState.update { currentState ->
                    currentState.copy(
                        favoriteMovies = it
                    )
                }
            }
        }
    }
}