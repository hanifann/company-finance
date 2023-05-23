@file:OptIn(ExperimentalMaterial3Api::class)

package com.ebt.finance.features.admin.tambah_data.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ebt.finance.features.admin.tambah_data.domain.model.Distributor
import com.ebt.finance.features.admin.tambah_data.domain.model.DistributorData
import com.ebt.finance.ui.theme.Accent
import com.ebt.finance.ui.theme.Primary
import com.ebt.finance.ui.theme.Subtitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuBoxComponent(
    distributor: Distributor,
    dropdownValue: String,
    onClick: (DistributorData)-> Unit,
    isExpanded: Boolean,
    onExpandChange: (Boolean)-> Unit,
    onDismiss: ()->Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = onExpandChange,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextField(
                value = dropdownValue,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Primary,
                    cursorColor = Color.Green,
                    placeholderColor = Color(
                        103,
                        115,
                        136
                    ),
                    unfocusedIndicatorColor = Color(
                        103,
                        115,
                        136,
                        50
                    ),
                    focusedIndicatorColor = Accent,
                ),
                placeholder = {
                    Text(
                        text = "Distributor",
                        color = Subtitle
                    )
                },
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = onDismiss,
            ) {
                distributor.data.forEach { item ->
                    DropdownMenuItem(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = { Text(text = item.namaDistributor) },
                        onClick = {onClick(item)}
                    )
                }
            }
        }
    }
}