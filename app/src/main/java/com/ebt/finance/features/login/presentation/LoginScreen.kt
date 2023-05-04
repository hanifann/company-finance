package com.ebt.finance.features.login.presentation

import android.graphics.drawable.Icon
import android.widget.Space
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ebt.finance.R
import com.ebt.finance.ui.theme.Accent
import com.ebt.finance.ui.theme.Primary
import com.ebt.finance.ui.theme.Secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
) {
    var email by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_alternate_email_24),
                contentDescription = "email icon",
                tint = Color(
                    103,
                    115,
                    136
                )
            )
            Spacer(
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )
            BasicTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    color = Secondary
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                singleLine = true,
            ) {
                TextFieldDefaults.TextFieldDecorationBox(
                    value = email.text,
                    innerTextField = it,
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    contentPadding = PaddingValues(
                        horizontal = 0.dp,
                        vertical = 8.dp
                    ),
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
                            text = "Email"
                        )
                    }
                )
            }
        }
    }
}