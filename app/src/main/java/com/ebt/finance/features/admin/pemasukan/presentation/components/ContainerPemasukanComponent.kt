package com.ebt.finance.features.admin.pemasukan.presentation.components

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ebt.finance.ui.theme.Accent
import com.ebt.finance.ui.theme.Secondary
import com.ebt.finance.ui.theme.Subtitle
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ContainerPemasukanComponent(
    title: String,
    subtitle: String,
    untung: String,
    date: String,
    icon: Int,
    onTap: ()-> Unit
) {
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
                            id = icon
                        ),
                        contentDescription = "untung",
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
                Text(
                    text = title,
                    color = Secondary,
                    fontWeight = FontWeight(500),
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.padding(vertical = 2.dp))
                Text(
                    text = subtitle,
                    color = Subtitle,
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.4f),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = untung,
                    color = Secondary,
                    fontWeight = FontWeight(500),
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textAlign = TextAlign.End
                )
                Spacer(modifier = Modifier.padding(vertical = 2.dp))
                SimpleDateFormat("yyyy-MM-dd", Locale("id")).parse(date)?.let {
                    Text(
                        text = SimpleDateFormat("dd MMMM yyyy", Locale("id")).format(it),
                        color = Subtitle,
                        fontWeight = FontWeight(400),
                        fontSize = 14.sp,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}