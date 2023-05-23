package com.ebt.finance.features.admin.tambah_data.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ColumnTitleAndTextField(
    title: String,
    textField: @Composable ()-> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            fontWeight = FontWeight(600),
            fontSize = 18.sp
        )
        textField()
    }
}