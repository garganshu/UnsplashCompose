package com.test.unleashdemo.ui.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.unleashdemo.ui.data.ImageData
import com.test.unleashdemo.ui.domain.MainRepository
import com.test.unleashdemo.utils.UnleashResponse
import com.test.unleashdemo.utils.ViewState
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _dataListener = MutableLiveData<ViewState<List<ImageData>>>()
    val dataListener: LiveData<ViewState<List<ImageData>>> = _dataListener

    fun fetchData() {
        viewModelScope.launch {
            _dataListener.postValue(ViewState.Loading(setLoading = true))
            val viewState = when (val response = repository.getData()) {
                is UnleashResponse.Success -> {
                    ViewState.Success(response.data)
                }
                is UnleashResponse.Failure -> {
                    ViewState.Error(response.error)
                }
            }
            _dataListener.postValue(viewState)
        }
    }
}