package com.test.unleashdemo.ui.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val dataState = viewModel.dataFlow.collectAsState().value
            val imageData = viewModel.imageDataFlow.collectAsState().value
            val isImageDetailsToggleEnabled =
                viewModel.imageDetailsToggleListener.collectAsState().value
            MainScreen(
                imageDataList = dataState,
                selectedImage = imageData,
                onFinish = { finish() },
                onSelectItem = {
                    if (isImageDetailsToggleEnabled) {
                        viewModel.selectImage(it)
                    }
                },
                isImageDetailsEnabled = isImageDetailsToggleEnabled
            )
        }
        viewModel.run {
            fetchData()
            getImageDetailsToggleState()
        }
    }
}