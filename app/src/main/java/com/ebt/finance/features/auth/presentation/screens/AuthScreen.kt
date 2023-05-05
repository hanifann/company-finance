package com.ebt.finance.features.auth.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ebt.finance.features.auth.presentation.viewmodel.AuthViewModel

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navController: NavController
) {

    val token: String by viewModel.token.collectAsState()
    if(token.isEmpty() || token.isBlank()) {
        navController.navigate("login_screen"){
            popUpTo(0)
        }
    } else {
        navController.navigate("home_admin"){
            popUpTo(0)
        }
    }
}