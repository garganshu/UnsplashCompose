package com.test.unsplashcompose.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.unsplashcompose.ui.data.ImageData
import com.test.unsplashcompose.ui.domain.MainUseCase
import com.test.unsplashcompose.utils.Response
import com.test.unsplashcompose.utils.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCase: MainUseCase
) : ViewModel() {

    private val _dataFlow: MutableStateFlow<ViewState<List<ImageData>>> =
        MutableStateFlow(ViewState.Loading(setLoading = true))
    val dataFlow: StateFlow<ViewState<List<ImageData>>> = _dataFlow

    private val _isImageDetailsEnabled: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isImageDetailsEnabled: StateFlow<Boolean> = _isImageDetailsEnabled

    private val _isRefreshing: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    fun fetchData() {
        viewModelScope.launch {
            val viewState = when (val response = useCase.getData()) {
                is Response.Success -> {
                    ViewState.Success(response.data)
                }
                is Response.Failure -> {
                    ViewState.Error(response.error)
                }
            }
            _dataFlow.emit(viewState)
            _isRefreshing.emit(false)
        }
    }

    fun fetchImageDetailsState() {
        viewModelScope.launch {
            val isEnabled = useCase.isImageDetailsToggleEnabled()
            _isImageDetailsEnabled.value = isEnabled
        }
    }

    fun downloadImage(imageData: ImageData) {
        viewModelScope.launch {
            useCase.downloadImage(imageData)
        }
    }
}