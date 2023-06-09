package com.ebt.finance

sealed class Route (val route: String) {
    object LoginScren: Route("login_screen")
    object AuthScreen: Route("auth_screen")
    object HomeAdminScreen: Route("home_admin")
    object PemasukanDetailScreen: Route("income_detail")
    object PengeluaranDetailScreen: Route("expanse_detail")
    object ImageViewerScreen: Route("image_viewer")
    object TambahDataScreen: Route("tambah_data")
    object UpdateDataScreen: Route("update_data")
    object HomePegawaiScreen: Route("home_pegawai")
    object GajiDetailScreen: Route("gaji_detail")
}