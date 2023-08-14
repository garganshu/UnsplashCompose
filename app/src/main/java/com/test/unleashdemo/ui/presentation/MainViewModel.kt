package com.test.unleashdemo.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.unleashdemo.ui.data.ImageData
import com.test.unleashdemo.ui.data.emptyImage
import com.test.unleashdemo.ui.domain.MainRepository
import com.test.unleashdemo.utils.UnleashResponse
import com.test.unleashdemo.utils.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _dataFlow: MutableStateFlow<ViewState<List<ImageData>>> = MutableStateFlow(ViewState.Loading(setLoading = true))
    val dataFlow: StateFlow<ViewState<List<ImageData>>> = _dataFlow

    private val _imageDataFlow: MutableStateFlow<ImageData> = MutableStateFlow(emptyImage())
    val imageDataFlow: StateFlow<ImageData> = _imageDataFlow

    private val _imageDetailsToggleListener: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val imageDetailsToggleListener: StateFlow<Boolean> = _imageDetailsToggleListener

    fun fetchData() {
        viewModelScope.launch {
            val viewState = when (val response = repository.getData()) {
                is UnleashResponse.Success -> {
                    ViewState.Success(response.data)
                }
                is UnleashResponse.Failure -> {
                    ViewState.Error(response.error)
                }
            }
            _dataFlow.emit(viewState)
        }
    }

    fun selectImage(image: ImageData) {
        viewModelScope.launch {
            _imageDataFlow.emit(image)
        }
    }

    fun getImageDetailsToggleState() {
        viewModelScope.launch {
            val isEnabled = repository.isImageDetailsToggleEnabled()
            _imageDetailsToggleListener.emit(isEnabled)
        }
    }
}