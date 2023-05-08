package com.ebt.finance

sealed class Route (val route: String) {
    object LoginScren: Route("login_screen")
    object AuthScreen: Route("auth_screen")
    object HomeAdminScreen: Route("home_admin")
    object PemasukanDetailScreen: Route("income_detail")
}