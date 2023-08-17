package com.test.unleashdemo.ui.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.test.unleashdemo.ui.data.ImageData
import com.test.unleashdemo.utils.ViewState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ContentScreen(
    viewState: ViewState<List<ImageData>>,
    onItemClick: (ImageData) -> Unit,
    isRefreshing: Boolean,
    onRefresh: () -> Unit
) {
    val context = LocalContext.current
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = onRefresh
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .pullRefresh(pullRefreshState)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Black),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(imageDataList.size) { i ->
                            val image = imageDataList[i]
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                AsyncImage(
                                    modifier = Modifier.clickable {
                                        onItemClick(image)
                                    },
                                    model = image.url,
                                    contentDescription = image.contentDescription,
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                    PullRefreshIndicator(modifier = Modifier.align(Alignment.TopCenter), refreshing = isRefreshing, state = pullRefreshState)
                }
            }
        }
    }
}