package com.ebt.finance.features.image_viewer.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ebt.finance.common.Constant
import com.ebt.finance.features.image_viewer.presentation.viewModel.ImageViewerViewModel
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  ImageViewerScreen(
    navController: NavController,
    viewModel: ImageViewerViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Black
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back icon",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        content = {
            AsyncImage(
                model = "${Constant.BASE_URL}upload/${state.imageViewer.category}/${state.imageViewer.imageUrl}",
                contentDescription = "image",
                modifier = Modifier
                    .zoomable(rememberZoomState())
                    .fillMaxSize(),
                onState = {
                    Log.d("image", "${Constant.BASE_URL}upload/${state.imageViewer.category}/${state.imageViewer.imageUrl}")
                }
            )
        }
    )
}