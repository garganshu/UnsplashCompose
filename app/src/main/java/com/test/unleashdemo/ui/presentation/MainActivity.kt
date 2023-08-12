package com.test.unleashdemo.ui.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.test.unleashdemo.utils.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpView()
    }

    private fun setUpView() {
        observe()
        viewModel.fetchData()
    }

    private fun observe() {
        viewModel.dataListener.observe(this) { viewState ->
            when(viewState) {
                is ViewState.Loading -> {
                    Log.d("CHECKDATA: ", "Loading")
                }
                is ViewState.Success -> {
                    Log.d("CHECKDATA: ", "${viewState.data}")
                }
                is ViewState.Error -> {
                    Log.d("CHECKDATA: ", viewState.error)
                }
            }
        }
    }
}