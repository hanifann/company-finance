package com.ebt.finance.features.pegawai.gaji.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.ebt.finance.R
import com.ebt.finance.common.OnLifecycleEvent
import com.ebt.finance.features.pegawai.gaji.presentation.components.ContainerDataGaji
import com.ebt.finance.features.pegawai.gaji.presentation.viewmodel.GajiViewModel
import com.ebt.finance.ui.theme.Accent
import com.ebt.finance.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePegawaiScreen(
    navController: NavController,
    viewModel: GajiViewModel = hiltViewModel(),
){
    val gajiState = viewModel.gajiState.value

    OnLifecycleEvent{ _, event ->
        when(event){
            Lifecycle.Event.ON_START -> viewModel.getListGaji()
            else -> {}
        }
    }
    
    Scaffold(
        containerColor = Primary,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Accent
                ),
                title = {
                    Column {
                        Text(
                            text = "Riwayat gaji",
                            color = Color.White,
                            fontWeight = FontWeight(600)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.logOut()
                            navController.navigate("auth_screen")
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.baseline_logout_24,
                            ),
                            contentDescription = "logout icon",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .padding(
                        top = it.calculateTopPadding() + 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
                    .fillMaxSize(),
            ){
                items(
                    gajiState.gaji.data.size
                ) {index ->
                    ContainerDataGaji(
                        data = gajiState.gaji.data[index],
                        onTap = {
                            navController.navigate("gaji_detail/${gajiState.gaji.data[index].id}")
                        },
                        viewModel
                    )
                    Spacer(
                        modifier = Modifier
                            .padding(vertical = 6.dp)
                    )
                }
            }

            if(gajiState.isLoading) {
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

            if(gajiState.message.isNotBlank()){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = gajiState.message,
                    )
                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                    ElevatedButton(
                        onClick = {
                            viewModel.getListGaji()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Accent
                        )
                    ) {
                        Text(
                            text = "Coba lagi",
                            color = Color.White
                        )
                    }
                }
            }
        }
    )

}