package com.ebt.finance.features.image_viewer.presentation.state

import com.ebt.finance.features.image_viewer.presentation.domain.ImageViewer

data class ImageViewerState(
    val imageViewer: ImageViewer = ImageViewer(imageUrl = "", category = "")
)
