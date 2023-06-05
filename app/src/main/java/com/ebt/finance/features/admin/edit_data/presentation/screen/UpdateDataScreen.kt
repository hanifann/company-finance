package com.ebt.finance.features.admin.edit_data.presentation.screen
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ebt.finance.common.Constant
import com.ebt.finance.features.admin.edit_data.presentation.viewmodels.UpdateDataViewModel
import com.ebt.finance.features.admin.tambah_data.domain.model.TambahData
import com.ebt.finance.features.admin.tambah_data.presentation.components.ColumnTitleAndTextField
import com.ebt.finance.features.admin.tambah_data.presentation.components.ExposedDropdownMenuBoxComponent
import com.ebt.finance.features.login.presentation.component.CustomTextFieldComponent
import com.ebt.finance.ui.theme.Accent
import com.ebt.finance.ui.theme.Primary
import com.ebt.finance.ui.theme.Secondary
import com.ebt.finance.ui.theme.Subtitle
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateDataScreen(
    viewModel: UpdateDataViewModel = hiltViewModel(),
    navController: NavController,
) {

    val kategoriState = viewModel.kategoriState.value
    val distributorState = viewModel.distributorState.value
    val updatePemasukanState = viewModel.updatePemasukanState.value
    val updatehPengeluaranState = viewModel.updatePengeluaranState.value
    val jenisPengeluaranState = viewModel.jenisPengeluaranState.value

    var dropDownValue by remember {
        mutableStateOf(updatePemasukanState.data!!.namaDistributor)
    }
    var isExpanded by remember {mutableStateOf(false)}

    var pemasukanTextFieldValue by remember {
        mutableStateOf(TextFieldValue(updatePemasukanState.data!!.totalPemasukan))
    }
    val pemasukanInteractionSource = remember { MutableInteractionSource() }

    var tglTextFieldValue by remember {
        mutableStateOf(TextFieldValue(updatePemasukanState.data!!.tgl))
    }
    val tglInteractionSource = remember { MutableInteractionSource() }

    var keteranganDataTextFieldValue by remember {
        mutableStateOf(TextFieldValue(updatePemasukanState.data!!.keterangan))
    }
    val keteranganDataInteractionSource = remember { MutableInteractionSource() }

    val dialogState = rememberMaterialDialogState()

    var distributorId by remember {
        mutableStateOf(updatePemasukanState.data!!.distributorId)
    }

    var errorDialogShow by remember { mutableStateOf(false) }
    var successDialogShow by remember { mutableStateOf(false) }



    Scaffold(
        containerColor = Primary,
        topBar = {
            TopAppBar(
                colors = topAppBarColors(Accent),
                title = { Text(text = "Ubah ${kategoriState.kategori}", color = Color.White) },
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = it.calculateTopPadding() + 16.dp, horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                ColumnTitleAndTextField(
                    title = "Total ${kategoriState.kategori}",
                    textField = {
                        CustomTextFieldComponent(
                            textFieldValue = pemasukanTextFieldValue,
                            onValueChange = { value ->
                                pemasukanTextFieldValue = value
                            },
                            interactionSource = pemasukanInteractionSource,
                            placeholder = "Jumlah ${kategoriState.kategori}",
                            keyboardType = KeyboardType.Decimal
                        )
                    }
                )
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                Box(
                    modifier = Modifier
                        .clickable {
                            dialogState.show()
                        }
                ) {
                    ColumnTitleAndTextField(
                        title = "Tanggal ${kategoriState.kategori}",
                        textField = {
                            CustomTextFieldComponent(
                                textFieldValue = tglTextFieldValue,
                                onValueChange = { value ->
                                    tglTextFieldValue = value
                                },
                                interactionSource = tglInteractionSource,
                                placeholder = "Tanggal ${kategoriState.kategori}",
                                keyboardType = KeyboardType.Decimal,
                                readOnly = true
                            )
                        },
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                if(kategoriState.kategori == "pemasukan"){
                    ColumnTitleAndTextField(
                        title = "Distributor",
                        textField = {
                            ExposedDropdownMenuBoxComponent(
                                dropdownValue = dropDownValue,
                                distributor = distributorState.distributor,
                                onClick = { item ->
                                    dropDownValue = item.namaDistributor
                                    isExpanded = !isExpanded
                                    distributorId = item.id
                                },
                                isExpanded = isExpanded,
                                onDismiss = {
                                    isExpanded = false
                                },
                                onExpandChange = {
                                    isExpanded = !isExpanded
                                }

                            )
                        }
                    )
                } else {
                    ColumnTitleAndTextField(
                        title = "Jenis Pengeluaran",
                        textField = {
                            ExposedDropdownMenuBoxComponent(
                                dropdownValue = dropDownValue,
                                distributor = jenisPengeluaranState.data,
                                onClick = { item ->
                                    dropDownValue = item.jenisPengeluaran
                                    isExpanded = !isExpanded
                                    distributorId = item.id
                                },
                                isExpanded = isExpanded,
                                onDismiss = {
                                    isExpanded = false
                                },
                                placeholder = "Jenis pengeluaran",
                                onExpandChange = {
                                    isExpanded = !isExpanded
                                },
                                isPemasukan = false

                            )
                        }
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                ColumnTitleAndTextField(
                    title = "Keterangan ${kategoriState.kategori}",
                    textField = {
                        CustomTextFieldComponent(
                            isSingleLine = false,
                            textFieldValue = keteranganDataTextFieldValue,
                            onValueChange = { value ->
                                keteranganDataTextFieldValue = value
                            },
                            interactionSource = keteranganDataInteractionSource,
                            placeholder = "Keterangan ${kategoriState.kategori}",
                            keyboardType = KeyboardType.Text
                        )
                    }
                )
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                ColumnTitleAndTextField(
                    title = "Bukti ${kategoriState.kategori}",
                    textField = {
                        Spacer(modifier = Modifier.padding(vertical = 4.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    1.dp,
                                    Subtitle,
                                    RoundedCornerShape(8.dp)
                                )
                                .height(256.dp)
                                .clickable {

                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if(kategoriState.kategori == "pemasukan"){
                                AsyncImage(
                                    model = "${Constant.BASE_URL}upload/pemasukan/${updatePemasukanState.data?.buktiPemasukan}",
                                    contentDescription = "bukti pemasukan",
                                    contentScale = ContentScale.FillBounds
                                )
                            } else {
                                AsyncImage(
                                    model = updatePemasukanState.data!!.buktiPemasukan,
                                    contentDescription = "bukti pemasukan",
                                    contentScale = ContentScale.FillBounds
                                )
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                ElevatedButton(
                    onClick = {
                        if(kategoriState.kategori == "pemasukan") {
                            viewModel.updatePemasukan(
                                TambahData(
                                    keterangan = keteranganDataTextFieldValue.text,
                                    bukti = "",
                                    distributorId = distributorId,
                                    totalHarga = pemasukanTextFieldValue.text,
                                    tgl = tglTextFieldValue.text
                                ),
                                updatePemasukanState.data!!.id
                            )
                        } else {


                        }
                    },
                    content = {
                        Text(
                            text = "Tambah ${kategoriState.kategori}",
                            color = Color.White
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Accent
                    ),
                    shape = RoundedCornerShape(4.dp)
                )
            }
        }
    )
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        datepicker(
            colors = DatePickerDefaults.colors(
                headerBackgroundColor = Accent,
                dateActiveBackgroundColor = Accent
            )
        ) { date ->
            tglTextFieldValue = TextFieldValue(date.format(DateTimeFormatter.ISO_LOCAL_DATE).toString())
        }
    }

    if(distributorState.isLoading || updatePemasukanState.isLoading || updatehPengeluaranState.isLoading) {
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

    if(updatePemasukanState.message.isNotBlank() || distributorState.message.isNotBlank()
        || updatehPengeluaranState.message.isNotBlank()){
        errorDialogShow = true
    }

    if(errorDialogShow){
        AlertDialog(
            onDismissRequest = {

            },
            title = {
                Text(text = "Error")
            },
            text = {
                if(distributorState.message.isNotBlank()){
                    Text(text = distributorState.message)
                } else {
                    if(kategoriState.kategori == "pemasukan"){
                        Text(text = updatePemasukanState.message)
                    } else {
                        Text(text = updatehPengeluaranState.message)
                    }
                }
            },
            confirmButton = {
                Text(
                    text = "Kembali",
                    modifier = Modifier
                        .clickable {
                            errorDialogShow = false
                            updatePemasukanState.message = ""
                            distributorState.message = ""
                            updatehPengeluaranState.message = ""
                        }
                )
            }
        )
    }

    if(updatePemasukanState.isSuccess || updatehPengeluaranState.isSuccess){
        successDialogShow = true
    }

    if(successDialogShow){
        AlertDialog(
            onDismissRequest = {
                navController.navigate("auth_screen"){
                    popUpTo(0)
                }
            },
            title = {
                Text(text = "Berhasil")
            },
            text = {
                Text(text = "Data berhasil diubah")
            },
            confirmButton = {

            },
            dismissButton = {
                Text(
                    text = "Kembali",
                    modifier = Modifier
                        .clickable {
                            successDialogShow = false
                            updatePemasukanState.isSuccess = false
                            updatehPengeluaranState.isSuccess = false
                            if(kategoriState.kategori == "pemasukan"){
                                navController.navigate("auth_screen"){
                                    popUpTo(0)
                                }
                            } else {
                                navController.navigate("auth_screen"){
                                    popUpTo(0)
                                }

                            }
                        }
                )
            }
        )
    }
}

