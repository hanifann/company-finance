package com.ebt.finance.features.image_viewer.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ebt.finance.common.Constant
import com.ebt.finance.features.image_viewer.presentation.domain.ImageViewer
import com.ebt.finance.features.image_viewer.presentation.state.ImageViewerState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val gson: Gson
): ViewModel() {

    private val _state = mutableStateOf(ImageViewerState())
    val state: State<ImageViewerState> = _state

    init {

        savedStateHandle.get<String>(Constant.PARAM_IMG_URL)?.let {
            if(it.isNotBlank()) {
                _state.value = ImageViewerState(gson.fromJson(it, ImageViewer::class.java))
            }
        }
    }
}