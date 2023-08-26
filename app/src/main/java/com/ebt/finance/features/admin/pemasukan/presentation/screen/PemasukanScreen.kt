package com.ebt.finance.features.admin.pemasukan.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.ebt.finance.R
import com.ebt.finance.common.OnLifecycleEvent
import com.ebt.finance.features.admin.pemasukan.presentation.components.ContainerPemasukanComponent
import com.ebt.finance.features.admin.pemasukan.presentation.components.ContainerTotalPemasukanComponent
import com.ebt.finance.features.admin.pemasukan.presentation.viewmodel.PemasukanViewModel
import com.ebt.finance.ui.theme.Accent
import com.ebt.finance.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PemasukanScreen(
    viewModel: PemasukanViewModel = hiltViewModel(),
    navController: NavController,
) {
    val state = viewModel.state.value
    val totalState = viewModel.totalState.value
    val tokenState = viewModel.tokenState.value
    
    val dates = viewModel.getDate().reversed()

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(dates[0]) }

    OnLifecycleEvent{ _, event ->
        when(event){
            Lifecycle.Event.ON_START -> viewModel.getToken()
            else -> {}
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
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

        if(state.isLoading || totalState.isLoading){
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Primary
                )
        ) {
            items(totalState.data.data.size){
                Text(
                    text = "Total Pemasukan",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    TextField(
                        value = selectedText,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        dates.forEach {
                            DropdownMenuItem(
                                text = {
                                    Text(text = it)
                                },
                                onClick = {
                                    selectedText = it
                                    expanded = false
                                    viewModel.getTotalPemasukan(tokenState.token, selectedText)
                                }
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                ) {
                    ContainerTotalPemasukanComponent(
                        title = "Jumlah pemasukan",
                        total = viewModel.formatCurrenty(totalState.data.data[0].jumlah_pemasukan.toDouble()),
                    )
                }
                Text(
                    text = "Daftar Pemasukan",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            items(state.data.data.size){
                ContainerPemasukanComponent(
                    title = state.data.data[it].namaDistributor,
                    subtitle = state.data.data[it].keterangan,
                    untung = viewModel.formatCurrenty(state.data.data[it].totalPemasukan.toDouble()),
                    date = state.data.data[it].tgl,
                    icon =  R.drawable.baseline_arrow_downward_24,
                    onTap = {
                        navController.navigate("income_detail/${state.data.data[it].id}/${state.data.data[it].namaDistributor}")
                    }
                )
                Spacer(modifier = Modifier.padding(vertical = 6.dp))
            }
        }


    }
}