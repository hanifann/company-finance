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
import androidx.hilt.navigation.compose.hiltViewModel
import com.ebt.finance.common.Constant
import com.ebt.finance.features.admin.edit_data.presentation.screen.UpdateDataScreen
import com.ebt.finance.features.admin.home.presentation.screen.HomeAdmin
import com.ebt.finance.features.admin.pemasukan.presentation.viewmodel.PemasukanViewModel
import com.ebt.finance.features.admin.pemasukan_detail.presentation.screen.PemasukanDetailScreen
import com.ebt.finance.features.admin.pengeluaran.presentation.viewmodel.PengeluaranViewModel
import com.ebt.finance.features.admin.pengeluaran_detail.presentation.screen.PengeluaranDetailScreen
import com.ebt.finance.features.admin.tambah_data.presentation.screen.TambahDataScreen
import com.ebt.finance.features.auth.presentation.screens.AuthScreen
import com.ebt.finance.features.image_viewer.presentation.screens.ImageViewerScreen
import com.ebt.finance.features.login.presentation.screen.LoginScreen
import com.ebt.finance.ui.theme.FinanceTheme
import com.ebt.finance.ui.theme.Primary
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

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
                    val pemasukanViewModel: PemasukanViewModel = hiltViewModel()
                    val pengeluaranViewModel: PengeluaranViewModel = hiltViewModel()
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
                            HomeAdmin(
                                navController = navController,
                                pemasukanViewModel = pemasukanViewModel,
                                pengeluaranViewModel = pengeluaranViewModel,
                                pemasukanState = pemasukanViewModel.state.value,
                                pengeluaranState = pengeluaranViewModel.state.value
                            )
                        }
                        composable(
                            route = Route.PemasukanDetailScreen.route + "/{incomeId}/{distributor}"
                        ) {
                            PemasukanDetailScreen(navController = navController)
                        }
                        composable(
                            route = Route.PengeluaranDetailScreen.route + "/{expanseId}/{jenisPengeluaran}"
                        ) {
                            PengeluaranDetailScreen(navController = navController)
                        }
                        composable(
                            route = Route.ImageViewerScreen.route + "/{${Constant.PARAM_IMG_URL}}"
                        ) {
                            ImageViewerScreen(navController = navController)
                        }
                        composable(
                            route = Route.TambahDataScreen.route + "/{${Constant.PARAM_KATEGORI}}"
                        ) {
                            TambahDataScreen(
                                navController = navController,
                                pemasukanViewModel = pemasukanViewModel,
                                pengeluaranViewModel = pengeluaranViewModel
                            )
                        }
                        composable(
                            route = Route.UpdateDataScreen.route + "/{${Constant.PARAM_KATEGORI}}/{${Constant.PARAM_DATA}}"
                        ){
                            UpdateDataScreen(
                                navController = navController,
                                pemasukanViewModel = pemasukanViewModel,
                                pengeluaranViewModel = pengeluaranViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}