package com.ebt.finance.features.admin.presentation.screen

import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ebt.finance.features.admin.presentation.viewModel.HomeAdminViewModel

@Composable
fun HomeAdmin(
    navController: NavController,
    viewModel: HomeAdminViewModel = hiltViewModel()
) {
    ElevatedButton(onClick = {
        viewModel.logOut()
        navController.navigate("auth_screen"){
            popUpTo(0)
        }
    }) {
        Text(text = "Logout")
    }
}