package com.ebt.finance.features.pegawai.gaji_detail.presentaion.screen

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.applyCanvas
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ebt.finance.features.admin.pemasukan_detail.presentation.components.RowTextAndValueComponent
import com.ebt.finance.features.pegawai.gaji.presentation.viewmodel.GajiViewModel
import com.ebt.finance.features.pegawai.gaji_detail.presentaion.viewmodel.GajiDetailViewModel
import com.ebt.finance.ui.theme.Accent
import com.ebt.finance.ui.theme.Primary
import com.ebt.finance.ui.theme.Secondary
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun GajiDetailScreen(
    navController: NavController,
    viewModel: GajiDetailViewModel = hiltViewModel(),
    gajiViewModel: GajiViewModel = hiltViewModel()
) {
    val gajiDetailState = viewModel.gajiDetailState.value
    val storagePermissonState = rememberPermissionState(
        permission = Manifest.permission.READ_EXTERNAL_STORAGE
    )

    val context = LocalContext.current

    val view = LocalView.current
    val handler = Handler(Looper.getMainLooper())

//    OnLifecycleEvent{ _, event ->
//        when(event){
//            Lifecycle.Event.ON_START -> viewModel.getDetailGaji()
//            else -> {}
//        }
//    }

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
                            text = "Detail gaji",
                            color = Color.White,
                            fontWeight = FontWeight(600)
                        )
                    }
                },
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
        }
    ) {
        Column() {
            Box(
                modifier = Modifier
                    .padding(
                        top = it.calculateTopPadding() + 16.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        BorderStroke(
                            2.dp,
                            color = Color(241,241,241)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                if(gajiDetailState.gaji.id.isNotEmpty()){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.padding(top = 70.dp))
                        SimpleDateFormat("yyyy-MM", Locale("id")).parse(gajiDetailState.gaji.bulan)?.let { date ->
                            SimpleDateFormat("MMMM yyyy", Locale("id")).format(date)
                        }?.let { it1 ->
                            MultiStyleText(
                                text1 = "Gaji anda Bulan ",
                                color1 = Secondary,
                                text2 = it1,
                                color2 = Accent,
                                text3 = " sebesar",
                                color3 = Secondary
                            )
                        }
                        Text(
                            text = gajiViewModel.formatCurrenty(
                                gajiDetailState.gaji.total.toDouble()
                            ),
                            fontSize = 32.sp,
                            fontWeight = FontWeight(600)
                        )
                        Spacer(modifier = Modifier.padding(top = 64.dp))
                        Divider(
                            color = Color(241,241,241)
                        )
                        RowTextAndValueComponent(
                            title = "Total Gaji",
                            value = gajiViewModel.formatCurrenty(
                                gajiDetailState.gaji.total.toDouble()
                            ),
                            modifier = Modifier
                                .padding(
                                    top = 16.dp,
                                    start = 16.dp,
                                    end = 16.dp,
                                    bottom = 8.dp
                                ),
                            titleColor = Color(69,69,79),
                            subtitleColor = Color(69,69,79),
                            titleFontWeight = FontWeight.Bold,
                            subtitleFontWeight = FontWeight.Bold
                        )
                        Row(
                            modifier = Modifier
                                .height(IntrinsicSize.Min)
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                        ) {
                            Divider(
                                color = Color(241,241,241),
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(4.dp)
                            )
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                RowTextAndValueComponent(
                                    title = "Gaji pokok",
                                    value = gajiViewModel.formatCurrenty(
                                        gajiDetailState.gaji.gapok.toDouble()
                                    ),
                                    modifier = Modifier
                                        .padding(start = 16.dp),
                                    titleColor = Color(110,108,125),
                                    subtitleColor = Color(110,108,125),
                                    titleFontWeight = FontWeight.Bold,
                                    subtitleFontWeight = FontWeight.Bold
                                )
                                RowTextAndValueComponent(
                                    title = "Tunjangan",
                                    value = gajiViewModel.formatCurrenty(
                                        gajiDetailState.gaji.tunjangan.toDouble()
                                    ),
                                    modifier = Modifier
                                        .padding(start = 16.dp),
                                    titleColor = Color(110,108,125),
                                    subtitleColor = Color(110,108,125),
                                    titleFontWeight = FontWeight.Bold,
                                    subtitleFontWeight = FontWeight.Bold
                                )
                                RowTextAndValueComponent(
                                    title = "Makan dan transport",
                                    value = gajiViewModel.formatCurrenty(
                                        gajiDetailState.gaji.makanTransport.toDouble()
                                    ),
                                    modifier = Modifier
                                        .padding(start = 16.dp),
                                    titleColor = Color(110,108,125),
                                    subtitleColor = Color(110,108,125),
                                    titleFontWeight = FontWeight.Bold,
                                    subtitleFontWeight = FontWeight.Bold
                                )
                                RowTextAndValueComponent(
                                    title = "Lembur",
                                    value = gajiViewModel.formatCurrenty(
                                        gajiDetailState.gaji.lembur.toDouble()
                                    ),
                                    modifier = Modifier
                                        .padding(start = 16.dp),
                                    titleColor = Color(110,108,125),
                                    subtitleColor = Color(110,108,125),
                                    titleFontWeight = FontWeight.Bold,
                                    subtitleFontWeight = FontWeight.Bold
                                )
                                RowTextAndValueComponent(
                                    title = "Insentif",
                                    value = gajiViewModel.formatCurrenty(
                                        gajiDetailState.gaji.insentiv.toDouble()
                                    ),
                                    modifier = Modifier
                                        .padding(start = 16.dp),
                                    titleColor = Color(110,108,125),
                                    subtitleColor = Color(110,108,125),
                                    titleFontWeight = FontWeight.Bold,
                                    subtitleFontWeight = FontWeight.Bold
                                )
                                RowTextAndValueComponent(
                                    title = "Jaminan kesehatan",
                                    value = gajiViewModel.formatCurrenty(
                                        gajiDetailState.gaji.jamkes.toDouble()
                                    ),
                                    modifier = Modifier
                                        .padding(start = 16.dp),
                                    titleColor = Color(110,108,125),
                                    subtitleColor = Color(110,108,125),
                                    titleFontWeight = FontWeight.Bold,
                                    subtitleFontWeight = FontWeight.Bold
                                )
//                                RowTextAndValueComponent(
//                                    title = "Pinjaman",
//                                    value = gajiViewModel.formatCurrenty(
//                                        gajiDetailState.gaji.pinjaman.toDouble()
//                                    ),
//                                    modifier = Modifier
//                                        .padding(start = 16.dp),
//                                    titleColor = Color(110,108,125),
//                                    subtitleColor = Color(110,108,125),
//                                    titleFontWeight = FontWeight.Bold,
//                                    subtitleFontWeight = FontWeight.Bold
//                                )
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 16.dp))
                    }
                }
            }
            Box(
                modifier = Modifier.padding(16.dp)
            ) {
                ElevatedButton(
                    onClick = {
                        if (storagePermissonState.status.isGranted){
                            handler.postDelayed(Runnable {
                                val bmp = Bitmap.createBitmap(view.width, view.height,
                                    Bitmap.Config.ARGB_8888).applyCanvas {
                                    view.draw(this)
                                }
                                generatePDF(
                                    context,
                                    bmp,
                                    view.height,
                                    view.width,
                                    "${gajiDetailState.gaji.name}_${gajiDetailState.gaji.bulan}"
                                )


                            }, 1000)
                        } else {
                            storagePermissonState.launchPermissionRequest()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Accent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Accent,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Text(
                        text = "Cetak PDF",
                        color = Color.White
                    )
                }
                }
            }

        if(gajiDetailState.isLoading) {
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
}

@Composable
fun MultiStyleText(text1: String, color1: Color, text2: String, color2: Color, text3: String, color3: Color) {
    Text(buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = color1,
                fontWeight = FontWeight(500),
                fontSize = 18.sp
            )
        ) {
            append(text1)
        }
        withStyle(
            style = SpanStyle(
                color = color2,
                fontWeight = FontWeight(500),
                fontSize = 18.sp
            )
        ) {
            append(text2)
        }
        withStyle(
            style = SpanStyle(
                color = color3,
                fontWeight = FontWeight(500),
                fontSize = 18.sp
            ),
        ) {
            append(text3)
        }
    })
}

fun generatePDF(context: Context, bmp: Bitmap, height: Int, width: Int, name: String) {

    // declaring width and height
    // for our PDF file.

    // creating a bitmap variable
    // for storing our images

    // creating an object variable
    // for our PDF document.
    var pdfDocument: PdfDocument = PdfDocument()

    // two variables for paint "paint" is used
    // for drawing shapes and we will use "title"
    // for adding text in our PDF file.
    val paint: Paint = Paint()
    // on below line we are initializing our bitmap and scaled bitmap.



    // we are adding page info to our PDF file
    // in which we will be passing our pageWidth,
    // pageHeight and number of pages and after that
    // we are calling it to create our PDF.
    val myPageInfo: PdfDocument.PageInfo? =
        PdfDocument.PageInfo.Builder(width, height, 1).create()

    // below line is used for setting
    // start page for our PDF file.
    val myPage: PdfDocument.Page = pdfDocument.startPage(myPageInfo)

    // creating a variable for canvas
    // from our page of PDF.
    val canvas: Canvas = myPage.canvas

    canvas.drawBitmap(bmp, 0f,0f, paint)

    // after adding all attributes to our
    // PDF file we will be finishing our page.
    pdfDocument.finishPage(myPage)

    // below line is used to set the name of
    // our PDF file and its path.
    val path: File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    val file = File(path, "$name.pdf")

    try {
        // after creating a file name we will
        // write our PDF file to that location.
        pdfDocument.writeTo(FileOutputStream(file))

        // on below line we are displaying a toast message as PDF file generated..
        Toast.makeText(context, "PDF file generated..", Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        // below line is used
        // to handle error
        e.printStackTrace()

        // on below line we are displaying a toast message as fail to generate PDF
        Toast.makeText(context, "Fail to generate PDF file..", Toast.LENGTH_SHORT)
            .show()
    }
    // after storing our pdf to that
    // location we are closing our PDF file.
    pdfDocument.close()
}
