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
//    Column(
//        modifier = Modifier
//            .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        CircularProgressIndicator(
//            color = Accent
//        )
//    }

    if(!state.isLoading){
        if(state.isSuccess == true){
            if(userDataState.userData.roleId.isNotBlank()){
                if(userDataState.userData.roleId != "2"){
                    navController.navigate("home_admin"){
                        popUpTo(0)
                    }
                } else {
                    navController.navigate("home_pegawai"){
                        popUpTo(0)
                    }
                }
            }
        } else {
            navController.navigate("login_screen"){
                popUpTo(0)
            }
        }
    }
}