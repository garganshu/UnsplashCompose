package com.test.unleashdemo.ui.presentation

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.test.unleashdemo.R
import com.test.unleashdemo.ui.data.ImageData
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onFinish: () -> Unit
) {
    val selectedImage: MutableState<ImageData?> = remember { mutableStateOf(null) }
    val context = LocalContext.current
    BottomSheetScreen(sheetContent = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextData(text = selectedImage.value?.id, textSize = 22f)
            TextData(text = selectedImage.value?.contentDescription, textSize = 18f)
            TextData(text = selectedImage.value?.description, textSize = 16f)
            AsyncImage(model = selectedImage.value?.url, contentDescription = null)
        }
    },
        content = { scope, state ->
            ContentScreen(viewState = viewModel.dataFlow.collectAsState().value, onItemClick = { image ->
                if(viewModel.isImageDetailsEnabled.value) {
                    selectedImage.value = image
                    scope.launch {
                        if (state.isVisible) {
                            state.hide()
                        } else {
                            state.show()
                        }
                    }
                } else {
                    Toast.makeText(
                        context,
                        R.string.flag_disabled,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
            BackHandler {
                scope.launch {
                    if (state.isVisible)
                        state.hide()
                    else
                        onFinish()
                }
            }
        }
    )
}

@OptIn(ExperimentalUnitApi::class)
@Composable
private fun TextData(text: String?, textSize: Float) {
    if (!text.isNullOrEmpty()) {
        Text(
            text = text,
            color = Color.Black,
            fontSize = TextUnit(textSize, TextUnitType.Sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(6.dp)
        )
    }
}