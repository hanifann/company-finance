package com.ebt.finance.features.admin.pemasukan_detail.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ebt.finance.features.admin.pemasukan_detail.presentation.viewmodel.PemasukanDetailViewModel
import com.ebt.finance.ui.theme.Accent

@Composable
fun PemasukanDetailScreen(
    viewModel: PemasukanDetailViewModel = hiltViewModel()
){
    val state = viewModel.state.value

    if(state.error.isNotBlank()){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = state.error
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            ElevatedButton(
                onClick = {
                    viewModel.getToken(state.id)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Accent
                )
            ) {
                Text(text = "Coba lagi")
            }
        }
    }

    if(state.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = Accent
            )
        }
    }
}