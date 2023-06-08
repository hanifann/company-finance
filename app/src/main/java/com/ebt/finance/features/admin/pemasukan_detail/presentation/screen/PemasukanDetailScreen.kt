package com.ebt.finance.features.admin.pemasukan_detail.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ebt.finance.R
import com.ebt.finance.common.Constant
import com.ebt.finance.features.admin.pemasukan.domain.models.PemasukanData
import com.ebt.finance.features.admin.pemasukan_detail.presentation.components.RowTextAndValueComponent
import com.ebt.finance.features.admin.pemasukan_detail.presentation.viewmodel.PemasukanDetailViewModel
import com.ebt.finance.features.image_viewer.presentation.domain.ImageViewer
import com.ebt.finance.ui.theme.Accent
import com.ebt.finance.ui.theme.Primary
import com.ebt.finance.ui.theme.Secondary
import com.ebt.finance.ui.theme.Subtitle
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun PemasukanDetailScreen(
    viewModel: PemasukanDetailViewModel = hiltViewModel(),
    navController: NavController
){
    val state = viewModel.state.value
    val disState = viewModel.disState.value
    val delState = viewModel.delState.value
    val userDataState = viewModel.userDataState.value

    var isImageError by remember { mutableStateOf(false) }
    var errorDialog by remember { mutableStateOf(false) }
    var delDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Primary
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(state.data.data.size) {
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
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
                text = "Detail Pemasukan",
                fontWeight = FontWeight(500),
                fontSize = 24.sp
            )
            Text(
                text = "Berikut merupakan detail pemasukan",
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
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    RowTextAndValueComponent(
                        title = "Jumlah",
                        value = viewModel.formatCurrenty(state.data.data[0].totalPemasukan.toDouble()),
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 8.dp)
                    )
                    Divider(
                        color = Subtitle,
                        thickness = 1.dp
                    )
                    RowTextAndValueComponent(
                        title = "Nama distributor",
                        value = disState.distributor,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    )
                    RowTextAndValueComponent(
                        title = "Keterangan",
                        value = state.data.data[0].keterangan,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                    SimpleDateFormat("yyyy-MM-dd", Locale("id")).parse(state.data.data[0].tgl)
                        ?.let {
                            RowTextAndValueComponent(
                                title = "Tanggal",
                                value = SimpleDateFormat("dd MMMM, yyyy", Locale("id")).format(it),
                                modifier = Modifier
                                    .padding(bottom = 8.dp)
                            )
                        }
                    SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss",
                        Locale("id")
                    ).parse(state.data.data[0].updatedAt)?.let {
                        RowTextAndValueComponent(
                            title = "Jam",
                            value = SimpleDateFormat("HH:mm", Locale("id")).format(it),
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                        )
                    }
                    Divider(
                        color = Subtitle,
                        thickness = 1.dp
                    )
                    Text(
                        text = "Bukti pemasukan :",
                        fontWeight = FontWeight(300),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        color = Color(red = 180, green = 180, blue = 180),
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    )
                    if (isImageError) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .background(
                                    Color.LightGray,
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            contentAlignment = Alignment.Center,
                            content = {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_broken_image_24),
                                    contentDescription = "broken image",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .width(36.dp)
                                        .height(36.dp)
                                )
                            }
                        )
                    }
                    AsyncImage(
                        model = "${Constant.BASE_URL}upload/pemasukan/${state.data.data[0].buktiPemasukan}",
                        contentDescription = "bukti-pembayaran",
                        contentScale = ContentScale.Crop,
                        onError = {
                            isImageError = true
                        },
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 16.dp)
                            .clip(
                                RoundedCornerShape(8.dp)
                            )
                            .border(
                                color = Subtitle,
                                width = 1.dp,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable(
                                onClick = {
                                    navController.navigate(
                                        "image_viewer/${
                                            viewModel.toJson(
                                                image = ImageViewer(
                                                    imageUrl = state.data.data[0].buktiPemasukan,
                                                    category = "pemasukan"
                                                )
                                            )
                                        }"
                                    )
                                }
                            )

                    )
                }
            }
            if(userDataState.userData.roleId == "1"){
                OutlinedButton(
                    onClick = { delDialog = true },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(
                        1.dp,
                        Color.Red
                    ),
                    content = {
                        Text(
                            text = "Hapus pemasukan",
                            color = Color.Red,
                            fontWeight = FontWeight(500)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 2.dp))
            if(userDataState.userData.roleId == "1"){
                OutlinedButton(
                    onClick = {
                        navController.navigate(
                            "update_data/pemasukan/${
                                viewModel.paramToJson(
                                    PemasukanData(
                                        id = state.data.data[0].id,
                                        buktiPemasukan = state.data.data[0].buktiPemasukan,
                                        distributorId = state.data.data[0].distributorId,
                                        keterangan = state.data.data[0].keterangan,
                                        namaDistributor = disState.distributor,
                                        tgl = state.data.data[0].tgl,
                                        totalPemasukan = state.data.data[0].totalPemasukan,
                                        updatedAt = state.data.data[0].updatedAt

                                    )
                                )
                            }"
                        )
                    },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(
                        1.dp,
                        Accent
                    ),
                    content = {
                        Text(
                            text = "Edit pemasukan",
                            color = Accent,
                            fontWeight = FontWeight(500)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
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

    if(delState.error.isNotBlank()){
        errorDialog = true
    }

    if (errorDialog) {
        AlertDialog(
            onDismissRequest = {

            },
            title = {
                Text(text = "Error")
            },
            text = {
                Text(text = delState.error)
            },
            confirmButton = {
                Text(
                    text = "Kembali",
                    modifier = Modifier
                        .clickable {
                            errorDialog = false
                            delState.error = ""
                        }
                )
            }
        )
    }

    if(delState.isLoading) {
        AlertDialog(
            onDismissRequest = {

            },
            text = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    CircularProgressIndicator(
                        color = Accent
                    )
                    Spacer(modifier = Modifier.padding(vertical = 12.dp))
                    Text(
                        text = "Loading...",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Secondary
                    )
                }
            },
            confirmButton = {

            }
        )
    }

    if(delState.isSuccess){
        navController.navigate("auth_screen") {
            popUpTo(0)
        }
    }

    if (delDialog) {
        AlertDialog(
            onDismissRequest = {

            },
            title = {
                Text(text = "Hapus pemasukan")
            },
            text = {
                Text(text = "Apakah anda yakin ingin menghapus pemasukan?")
            },
            confirmButton = {
                Text(
                    text = "Ya",
                    color = Color.Red,
                    modifier = Modifier
                        .clickable {
                            viewModel.deletePemasukan()
                        }
                )
            },
            dismissButton = {
                Text(
                    text = "Tidak",
                    modifier = Modifier
                        .clickable {
                            delDialog = false
                        }
                        .padding(end = 16.dp)
                )
            }
        )
    }
}