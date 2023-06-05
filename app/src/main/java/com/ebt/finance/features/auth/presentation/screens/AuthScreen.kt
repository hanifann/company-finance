package com.ebt.finance.features.auth.presentation.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ebt.finance.features.auth.presentation.viewmodel.AuthViewModel

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navController: NavController
) {

    val state = viewModel.state.value
    val userDataState = viewModel.userDataState.value

    if(!state.isLoading){
        if(state.isSuccess == true){
            navController.navigate("home_admin"){
                popUpTo(0)
            }
        } else {
            navController.navigate("login_screen"){
                popUpTo(0)
            }
        }
    }
}