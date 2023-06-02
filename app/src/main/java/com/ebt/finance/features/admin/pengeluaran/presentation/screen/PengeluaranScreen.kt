package com.ebt.finance.features.admin.pengeluaran.presentation.screen

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
import androidx.navigation.NavController
import com.ebt.finance.R
import com.ebt.finance.features.admin.pemasukan.presentation.components.ContainerPemasukanComponent
import com.ebt.finance.features.admin.pengeluaran.presentation.state.PengeluaranState
import com.ebt.finance.features.admin.pengeluaran.presentation.viewmodel.PengeluaranViewModel
import com.ebt.finance.ui.theme.Accent
import com.ebt.finance.ui.theme.Primary

@Composable
fun PengeluaranScreen(
    navController: NavController,
    viewModel: PengeluaranViewModel,
    pengeluaranState: PengeluaranState
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Primary
            )
            .padding(16.dp)
    ) {
        items(pengeluaranState.data.data.size){
            ContainerPemasukanComponent(
                title = pengeluaranState.data.data[it].jenisPengeluaran,
                subtitle = pengeluaranState.data.data[it].keterangan,
                untung = viewModel.formatCurrenty(pengeluaranState.data.data[it].totalPengeluaran.toDouble()),
                date = pengeluaranState.data.data[it].tgl,
                icon =  R.drawable.baseline_arrow_upward_24,
                onTap = {
                    navController.navigate("expanse_detail/${pengeluaranState.data.data[it].id}/${pengeluaranState.data.data[it].jenisPengeluaran}")
                }
            )
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
        }
    }

    if(pengeluaranState.error.isNotBlank()){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = pengeluaranState.error
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

    if(pengeluaranState.isLoading){
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