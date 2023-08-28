package com.ebt.finance.features.admin.pemasukan.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.ebt.finance.ui.theme.Accent
import com.ebt.finance.ui.theme.Secondary

@Composable
fun ContainerTotalPemasukanComponent(
    title: String,
    total: String,
    isPemasukan: Boolean = true
) {
    Box(
        modifier = Modifier
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
            Icon(
                painter = if (isPemasukan) painterResource(id = R.drawable.income) else painterResource(id = R.drawable.expanse),
                contentDescription = "income icon",
                tint = Accent,
                modifier = Modifier
                    .size(64.dp,)
            )
            Spacer(modifier = Modifier.padding(horizontal = 12.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.6f),
            ) {
                Text(
                    text = title,
                    color = Secondary,
                    fontWeight = FontWeight(600),
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    text = total,
                    color = Secondary,
                    fontWeight = FontWeight(500),
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }

        }
    }
}