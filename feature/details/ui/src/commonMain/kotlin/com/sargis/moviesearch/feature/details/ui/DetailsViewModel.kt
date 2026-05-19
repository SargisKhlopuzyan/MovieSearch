package com.sargis.moviesearch.feature.details.ui

import androidx.lifecycle.SavedStateHandle
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.coroutineScope
import com.rickclephas.kmp.observableviewmodel.launch
import com.sargis.moviesearch.core.domain.onError
import com.sargis.moviesearch.core.domain.onSuccess
import com.sargis.moviesearch.feature.details.domain.usecase.DeleteMovieFromFavoritesUseCase
import com.sargis.moviesearch.feature.details.domain.usecase.GetMovieDetailsUseCase
import com.sargis.moviesearch.feature.details.domain.usecase.IsMoveFavoriteUseCase
import com.sargis.moviesearch.feature.details.domain.usecase.MarkAsFavoriteMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class DetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val deleteMovieFromFavoritesUseCase: DeleteMovieFromFavoritesUseCase,
    private val markAsFavoriteMovieUseCase: MarkAsFavoriteMovieUseCase,
    private val isMoveFavoriteUseCase: IsMoveFavoriteUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(viewModelScope, DetailsUiState())

    @NativeCoroutinesState
    val uiState = _uiState.asStateFlow()
        .onStart {
            // val movieId = savedStateHandle.toRoute<Dest.Details>().movieId
            // fetchMovieDetail(movieId)
            //            observeFavoriteStatus()
        }
        .stateIn(
            viewModelScope.coroutineScope,
            SharingStarted.WhileSubscribed(5_000L),
            _uiState.value
        )

    fun onAction(action: MovieDetailsAction) {
        when (action) {
            is MovieDetailsAction.OnSelectedMovieChanged -> {
                fetchMovieDetail(action.movieId)
                observeFavoriteStatus(action.movieId)
            }
            MovieDetailsAction.OnBackClick -> {}
            MovieDetailsAction.OnFavoriteClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    uiState.value.movieDetails?.let { movie ->
                        if (uiState.value.isFavorite) {
                            deleteMovieFromFavoritesUseCase.execute(movie.id)
                        } else {
                            markAsFavoriteMovieUseCase.execute(movie)
                        }
                    }
                }
            }
        }
    }

    private fun fetchMovieDetail(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                DetailsUiState(isLoading = true)
            }
            getMovieDetailsUseCase.execute(movieId)
                .onSuccess { data ->
                    _uiState.update {
                        DetailsUiState(isLoading = false, movieDetails = data)
                    }
                }.onError {
                    _uiState.update {
                        DetailsUiState(isLoading = false, error = it.error)
                    }
                }
        }
    }

    private fun observeFavoriteStatus(movieId: String) {
        viewModelScope.launch {
            isMoveFavoriteUseCase.execute(movieId)
                .onEach { isFavorite ->
                    _uiState.update { currentState ->
                        currentState.copy(isFavorite = isFavorite)
                    }
                }
                .collect()
        }
    }
}