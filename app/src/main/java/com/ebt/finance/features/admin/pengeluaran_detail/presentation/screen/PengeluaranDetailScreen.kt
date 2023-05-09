package com.ebt.finance.features.admin.pengeluaran_detail.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ebt.finance.R
import com.ebt.finance.features.admin.pemasukan_detail.presentation.components.RowTextAndValueComponent
import com.ebt.finance.features.admin.pengeluaran_detail.presentation.viewmodel.PengeluaranDetailViewModel
import com.ebt.finance.ui.theme.Accent
import com.ebt.finance.ui.theme.Primary
import com.ebt.finance.ui.theme.Subtitle
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun PengeluaranDetailScreen(
    viewModel: PengeluaranDetailViewModel = hiltViewModel(),
    navController: NavController
){
    val state = viewModel.state.value
    val disState = viewModel.jenisPengeluaranState.value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .background(
                color = Primary
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(state.data.data.size){
            Box(
                modifier = Modifier
                    .background(
                        color = Accent.copy(alpha = .2f),
                        shape = CircleShape
                    )
                    .padding(16.dp),
                content = {
                    Box(
                        modifier = Modifier
                            .background(
                                color = Accent,
                                shape = CircleShape
                            )
                            .padding(6.dp),
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_receipt_24),
                                contentDescription = "receipt",
                                tint = Primary
                            )
                        }
                    )
                }
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Text(
                text = "Detail pengeluaran",
                fontWeight = FontWeight(500),
                fontSize = 24.sp
            )
            Text(
                text = "Berikut merupakan detail pengeluaran",
                fontSize = 14.sp
            )
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(15.dp)
                    )
            ) {
                Column (
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    RowTextAndValueComponent(
                        title = "Jumlah",
                        value = viewModel.formatCurrenty(state.data.data[0].totalPengeluaran.toDouble()),
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    )
                    Divider(
                        color = Subtitle,
                        thickness = 1.dp
                    )
                    RowTextAndValueComponent(
                        title = "Jenis pengeluaran",
                        value = disState.jenisPengeluaran,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    )
                    RowTextAndValueComponent(
                        title = "Keterangan",
                        value = state.data.data[0].keterangan,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                    SimpleDateFormat("yyyy-MM-dd", Locale("id")).parse(state.data.data[0].tgl)?.let {
                        RowTextAndValueComponent(
                            title = "Tanggal",
                            value = SimpleDateFormat("dd MMMM, yyyy", Locale("id")).format(it),
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                        )
                    }
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("id")).parse(state.data.data[0].updatedAt)?.let {
                        RowTextAndValueComponent(
                            title = "Jam",
                            value = SimpleDateFormat("HH:mm", Locale("id")).format(it),
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                        )
                    }
                }
            }
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