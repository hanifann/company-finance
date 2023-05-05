package com.ebt.finance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ebt.finance.features.admin.presentation.screen.HomeAdmin
import com.ebt.finance.features.auth.presentation.screens.AuthScreen
import com.ebt.finance.features.login.presentation.screen.LoginScreen
import com.ebt.finance.ui.theme.FinanceTheme
import com.ebt.finance.ui.theme.Primary
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import com.google.accompanist.navigation.animation.composable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinanceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Primary
                ) {
                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Route.AuthScreen.route,
                        enterTransition = {EnterTransition.None },
                        exitTransition = {ExitTransition.None },
                        popEnterTransition = {EnterTransition.None },
                        popExitTransition = {ExitTransition.None },
                    ) {
                        composable(
                            route = Route.LoginScren.route
                        ) {
                            LoginScreen(navController = navController)
                        }
                        composable(
                            route = Route.AuthScreen.route
                        ){
                            AuthScreen(navController = navController)
                        }
                        composable(
                            route = Route.HomeAdminScreen.route
                        ){
                            HomeAdmin(navController = navController)
                        }
                    }
                }
            }
        }
    }
}