@file:OptIn(ExperimentalMaterial3Api::class)

package com.ebt.finance.features.admin.tambah_data.presentation.screen

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ebt.finance.R
import com.ebt.finance.features.admin.tambah_data.presentation.components.ColumnTitleAndTextField
import com.ebt.finance.features.admin.tambah_data.presentation.components.ExposedDropdownMenuBoxComponent
import com.ebt.finance.features.admin.tambah_data.presentation.viewmodels.TambahDataViewModel
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TambahDataScreen(
    viewModel: TambahDataViewModel = hiltViewModel(),
    navController: NavController
) {

    val kategoriState = viewModel.kategoriState.value
    val distributorState = viewModel.distributorState.value

    var dropDownValue by remember {mutableStateOf("")}
    var isExpanded by remember {mutableStateOf(false)}

    var pemasukanTextFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    val pemasukanInteractionSource = remember { MutableInteractionSource() }

    var tglTextFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    val tglInteractionSource = remember { MutableInteractionSource() }

    val dialogState = rememberMaterialDialogState()

    var selectedImage by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImage = uri }
    )

    Scaffold(
        containerColor = Primary,
        topBar = {
            TopAppBar(
                colors = topAppBarColors(Accent),
                title = { Text(text = "Tambah ${kategoriState.kategori}", color = Color.White) },
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
                ColumnTitleAndTextField(
                    title = "Distributor",
                    textField = {
                        ExposedDropdownMenuBoxComponent(
                            dropdownValue = dropDownValue,
                            distributor = distributorState.distributor,
                            onClick = { item ->
                                dropDownValue = item.namaDistributor
                                isExpanded = !isExpanded
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
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                ColumnTitleAndTextField(
                    title = "Total Pemasukan",
                    textField = {
                        CustomTextFieldComponent(
                            textFieldValue = pemasukanTextFieldValue,
                            onValueChange = { value ->
                                pemasukanTextFieldValue = value
                            },
                            interactionSource = pemasukanInteractionSource,
                            placeholder = "Jumlah pemasukan",
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
                        title = "Tanggal Pemasukan",
                        textField = {
                            CustomTextFieldComponent(
                                textFieldValue = tglTextFieldValue,
                                onValueChange = { value ->
                                    tglTextFieldValue = value
                                },
                                interactionSource = tglInteractionSource,
                                placeholder = "Tanggal pemasukan",
                                keyboardType = KeyboardType.Decimal,
                                readOnly = true
                            )
                        },
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                ColumnTitleAndTextField(
                    title = "Bukti pemasukan",
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
                                    singlePhotoPickerLauncher.launch(
                                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if(selectedImage == null){
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_file_upload_24),
                                        contentDescription = "image",
                                        tint = Color.LightGray,
                                        modifier = Modifier
                                            .height(32.dp)
                                            .width(32.dp)
                                    )
                                    Text(
                                        text = "Pilih bukti pemasukan",
                                        fontSize = 12.sp,

                                        )
                                }
                            } else {
                                AsyncImage(
                                    model = selectedImage,
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

    if(distributorState.isLoading) {
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
}

