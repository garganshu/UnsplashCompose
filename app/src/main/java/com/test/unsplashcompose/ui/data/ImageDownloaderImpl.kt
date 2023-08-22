package com.test.unsplashcompose.ui.data

import android.app.DownloadManager
import android.os.Environment
import androidx.core.net.toUri
import com.test.unsplashcompose.ui.domain.ImageDownloader

class ImageDownloaderImpl(private val downloadManager: DownloadManager) : ImageDownloader {

    override suspend fun downloadImage(imageData: ImageData) {
        val imageUrl = imageData.url
        if(!imageUrl.isNullOrEmpty()) {
            val request = DownloadManager.Request(imageUrl.toUri())
                .setMimeType("image/jpeg")
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setTitle("${imageData.id} Downloaded")
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "${imageData.id}.jpeg")

            downloadManager.enqueue(request)
        }
    }
}