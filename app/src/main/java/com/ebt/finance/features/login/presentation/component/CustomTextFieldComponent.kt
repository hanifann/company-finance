package com.ebt.finance.features.login.presentation.component

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ebt.finance.ui.theme.Accent
import com.ebt.finance.ui.theme.Primary
import com.ebt.finance.ui.theme.Secondary
import com.ebt.finance.ui.theme.Subtitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextFieldComponent(
    textFieldValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    interactionSource: InteractionSource,
    placeholder: String,
    keyboardType: KeyboardType,
    isVisible: Boolean = false,
    trailingIcon:  @Composable (() -> Unit) = {},
    readOnly: Boolean = false,
    isSingleLine: Boolean = true
) {

    BasicTextField(
        value = textFieldValue,
        onValueChange,
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = Secondary
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = isSingleLine,
        visualTransformation = if(isVisible) PasswordVisualTransformation() else VisualTransformation.None,
        readOnly = readOnly,
        enabled = !readOnly
        ) {
        TextFieldDefaults.TextFieldDecorationBox(
            value = textFieldValue.text,
            innerTextField = it,
            enabled = !readOnly,
            singleLine = isSingleLine,
            interactionSource = interactionSource,
            visualTransformation = if(isVisible) PasswordVisualTransformation() else VisualTransformation.None,
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
                    text = placeholder,
                    color = Subtitle
                )
            },
            trailingIcon = trailingIcon
        )
    }
}