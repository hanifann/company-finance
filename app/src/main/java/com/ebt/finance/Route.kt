package com.ebt.finance

sealed class Route (val route: String) {
    object LoginScren: Route("login_screen")
}