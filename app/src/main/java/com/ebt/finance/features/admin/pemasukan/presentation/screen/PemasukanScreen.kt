package com.ebt.finance.features.admin.pemasukan.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ebt.finance.features.admin.pemasukan.presentation.components.ContainerPemasukanComponent
import com.ebt.finance.features.admin.pemasukan.presentation.viewmodel.PemasukanViewModel
import com.ebt.finance.ui.theme.Accent
import com.ebt.finance.ui.theme.Primary

@Composable
fun PemasukanScreen(
    viewModel: PemasukanViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Primary
            )
            .padding(16.dp)
    ) {
        items(state.data.data.size){
            ContainerPemasukanComponent(
                title = state.data.data[it].namaDistributor,
                subtitle = state.data.data[it].keterangan,
                untung = viewModel.formatCurrenty(state.data.data[it].totalPemasukan.toDouble()),
                date = state.data.data[it].tgl,
                onTap = {
                    navController.navigate("income_detail/${state.data.data[it].id}")
                }
            )
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
        }
    }

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
                    viewModel.getToken()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Accent
                )
            ) {
                Text(text = "Coba lagi")
            }
        }
    }

    if(state.isLoading){
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