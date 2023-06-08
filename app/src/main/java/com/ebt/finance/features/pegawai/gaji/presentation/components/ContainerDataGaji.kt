package com.ebt.finance.features.pegawai.gaji.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ebt.finance.R
import com.ebt.finance.features.pegawai.gaji.domain.models.GajiData
import com.ebt.finance.features.pegawai.gaji.presentation.viewmodel.GajiViewModel
import com.ebt.finance.ui.theme.Accent
import com.ebt.finance.ui.theme.Secondary
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ContainerDataGaji(
    data: GajiData,
    onTap: ()-> Unit,
    viewModel: GajiViewModel
){
    Box(
        modifier = Modifier
            .clickable(
                onClick = onTap
            )
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp),
            )
            .shadow(
                elevation = 1.dp
            )
            .padding(vertical = 10.dp, horizontal = 12.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = Accent,
                        shape = CircleShape
                    )
                    .padding(all = 12.dp),
                content = {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.baseline_receipt_24
                        ),
                        contentDescription = "gaji",
                        tint = Color.White
                    )
                },
                contentAlignment = Alignment.Center
            )
            Spacer(modifier = Modifier.padding(horizontal = 6.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.6f),
            ) {
                SimpleDateFormat("yyyy-MM", Locale("id")).parse(data.bulan)?.let {
                    Text(
                        text = SimpleDateFormat("MMMM yyyy", Locale("id")).format(it),
                        color = Secondary,
                        fontWeight = FontWeight(500),
                        fontSize = 18.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 2.dp))
                Text(
                    text = viewModel.formatCurrenty(data.total.toDouble()),
                    color = Secondary,
                    fontWeight = FontWeight(400),
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}