package com.ebt.finance.features.login.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ebt.finance.R
import com.ebt.finance.common.DataStoreRepositoryImpl
import com.ebt.finance.features.login.presentation.component.LoginTextFieldComponent
import com.ebt.finance.features.login.presentation.viewmodel.LoginViewModel
import com.ebt.finance.ui.theme.Accent
import com.ebt.finance.ui.theme.Secondary

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val emailInteractionSource = remember { MutableInteractionSource() }
    val passwordInteractionSource = remember { MutableInteractionSource() }

    var emailTextFieldValue by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var passwordTextFieldValue by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var showDialog by remember { mutableStateOf(false) }

    val state = viewModel.state.value
    if(state.isLoading) {
        AlertDialog(
            onDismissRequest = {

            },
            text = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.padding(vertical = 12.dp))
                    Text(
                        text = "Loading...",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Secondary
                    )
                }
            },
            confirmButton = {
                showDialog = false
            }
        )
    }
    if(state.error.isNotBlank()){
        AlertDialog(
            onDismissRequest = {

            },
            title = {
                Text(text = "Error")
            },
            text = {
                Text(text = state.error)
            },
            confirmButton = {
                showDialog = false
            }
        )
    }

    if (!state.data?.data.isNullOrBlank()){
        navController.navigate("auth_screen")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(
            modifier = Modifier
                .padding(vertical = 12.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.pvc),
            contentDescription = "sign in logo",
            modifier = Modifier
                .fillMaxWidth()
                .requiredSize(256.dp)
        )
        Spacer(
            modifier = Modifier
                .padding(vertical = 24.dp)
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
                interactionSource = emailInteractionSource,
                placeholder = "Email",
                keyboardType = KeyboardType.Email,
                textFieldValue = emailTextFieldValue,
                onValueChange = {
                    emailTextFieldValue = it
                }
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
                imageVector = Icons.Outlined.Lock,
                contentDescription = "password iconn",
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
                keyboardType = KeyboardType.Password,
                textFieldValue = passwordTextFieldValue,
                onValueChange = {
                    passwordTextFieldValue = it
                }
            )
        }
        Spacer(
            modifier = Modifier
                .padding(vertical = 16.dp)
        )
        ElevatedButton(
            onClick = {
                viewModel.postLogin(
                    emailTextFieldValue.text,
                    passwordTextFieldValue.text
                )
            },
            contentPadding = PaddingValues(top = 10.dp, bottom = 9.dp),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Accent,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Masuk",
                fontSize = 18.sp,
                fontWeight = FontWeight(500)
            )
        }
    }
}