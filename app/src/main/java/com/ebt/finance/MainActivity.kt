package com.ebt.finance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ebt.finance.features.login.presentation.screen.LoginScreen
import com.ebt.finance.ui.theme.FinanceTheme
import com.ebt.finance.ui.theme.Primary
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinanceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Primary
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Route.LoginScren.route,
                    ) {
                        composable(
                            route = Route.LoginScren.route
                        ) {
                            LoginScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}