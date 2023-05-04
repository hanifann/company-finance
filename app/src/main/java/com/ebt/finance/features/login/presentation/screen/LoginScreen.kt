package com.ebt.finance.features.login.presentation.screen

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ebt.finance.R
import com.ebt.finance.features.login.presentation.component.LoginTextFieldComponent

@Composable
fun LoginScreen(
    navController: NavController,
) {
    val emailInteractionSource = remember { MutableInteractionSource() }
    val passwordInteractionSource = remember { MutableInteractionSource() }

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
            LoginTextFieldComponent(
                interactionSource = emailInteractionSource,
                placeholder = "Email",
                keyboardType = KeyboardType.Email
            )
        }
        Spacer(
            modifier = Modifier
            .padding(vertical = 12.dp)
        )
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
            LoginTextFieldComponent(
                interactionSource = passwordInteractionSource,
                placeholder = "Password",
                keyboardType = KeyboardType.Password
            )
        }
    }
}