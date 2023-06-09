package com.ebt.finance.features.admin.pemasukan_detail.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun RowTextAndValueComponent(
    title: String,
    value: String,
    modifier: Modifier,
    titleColor: Color = Color(red = 180, green = 180, blue = 180),
    subtitleColor: Color = Color.Black,
    titleFontWeight: FontWeight = FontWeight(300),
    subtitleFontWeight: FontWeight = FontWeight(500)
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontWeight = titleFontWeight,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            color = titleColor,
            modifier = Modifier
                .weight(.5f)
        )
        Text(
            text = value,
            overflow = TextOverflow.Ellipsis,
            fontWeight = subtitleFontWeight,
            maxLines = 1,
            textAlign = TextAlign.End,
            modifier = Modifier
                .weight(.5f),
            color = subtitleColor
        )
    }
}