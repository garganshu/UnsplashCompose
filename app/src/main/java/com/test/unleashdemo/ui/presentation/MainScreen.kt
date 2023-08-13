package com.test.unleashdemo.ui.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.test.unleashdemo.ui.data.ImageData
import com.test.unleashdemo.utils.ViewState

@Composable
fun MainScreen(
    viewState: ViewState<List<ImageData>>
) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (viewState) {
            is ViewState.Loading -> {
                CircularProgressIndicator()
            }
            is ViewState.Error -> {
                Toast.makeText(
                    context,
                    viewState.error,
                    Toast.LENGTH_LONG
                ).show()
            }
            is ViewState.Success -> {
                val imageDataList = viewState.data
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(imageDataList.size) { i ->
                        val item = imageDataList[i]
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .clickable {
                                    Log.d("Check: ", "Item Clicked")
                                },
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            AsyncImage(
                                model = item.imageUrl,
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }

            }
        }
    }

}