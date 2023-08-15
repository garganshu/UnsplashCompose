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
            val isImageDetailsEnabled = viewModel.isImageDetailsEnabled
            MainScreen(
                imageDataList = dataState,
                onFinish = { finish() },
                isDetailsEnabled = {
                    viewModel.fetchImageDetailsState()
                    isImageDetailsEnabled.value
                },
            )
        }
        viewModel.fetchData()
    }
}