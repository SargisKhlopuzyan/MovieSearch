package com.sargis.moviesearch.feature.details.ui

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import com.sargis.moviesearch.feature.details.domain.usecase.GetMovieDetailsUseCase
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(viewModelScope, DetailsUiState())

    @NativeCoroutinesState
    val uiState = _uiState.asStateFlow()

    fun getMovie(movieId: String) {
        viewModelScope.launch {
            _uiState.update {
                DetailsUiState(isLoading = true)
            }
            getMovieDetailsUseCase.execute(movieId)
                .onSuccess { data ->
                    _uiState.update {
                        DetailsUiState(isLoading = false, data = data)
                    }
                }.onFailure {
                    _uiState.update {
                        DetailsUiState(isLoading = false, error = it.error)
                    }
                }
        }
    }
}