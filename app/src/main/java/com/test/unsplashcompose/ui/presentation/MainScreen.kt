package com.test.unsplashcompose.ui.presentation

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.test.unsplashcompose.R
import com.test.unsplashcompose.ui.data.ImageData
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
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                TextData(text = selectedImage.value?.id, textSize = 22f)
                Image(
                    modifier = Modifier
                        .requiredSizeIn(4.dp, 4.dp, 64.dp, 64.dp)
                        .padding(6.dp)
                        .clickable {
                            selectedImage.value?.let {
                                viewModel.downloadImage(it)
                            }
                        },
                    painter = painterResource(id = R.drawable.ic_download),
                    contentDescription = null
                )
            }
            TextData(text = selectedImage.value?.contentDescription, textSize = 18f)
            TextData(text = selectedImage.value?.description, textSize = 16f)
            AsyncImage(model = selectedImage.value?.url, contentDescription = null)
        }
    },
        content = { scope, state ->
            val isRefreshing by viewModel.isRefreshing.collectAsState()
            ContentScreen(
                viewState = viewModel.dataFlow.collectAsState().value, onItemClick = { image ->
                    viewModel.fetchImageDetailsState()
                    if (viewModel.isImageDetailsEnabled.value) {
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
                },
                isRefreshing = isRefreshing,
                onRefresh = {
                    viewModel.fetchData()
                }
            )
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

@Composable
private fun TextData(
    modifier: Modifier = Modifier,
    text: String?,
    textSize: Float,
    color: Color = Color.Black
) {
    if (!text.isNullOrEmpty()) {
        Text(
            text = text,
            color = color,
            fontSize = TextUnit(textSize, TextUnitType.Sp),
            textAlign = TextAlign.Center,
            modifier = modifier.padding(6.dp)
        )
    }
}