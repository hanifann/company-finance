package com.ebt.finance.features.admin.home.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ebt.finance.R
import com.ebt.finance.features.admin.home.presentation.viewModel.HomeAdminViewModel
import com.ebt.finance.features.admin.pemasukan.presentation.screen.PemasukanScreen
import com.ebt.finance.features.admin.pemasukan.presentation.state.GetPemasukanState
import com.ebt.finance.features.admin.pemasukan.presentation.viewmodel.PemasukanViewModel
import com.ebt.finance.features.admin.pengeluaran.presentation.screen.PengeluaranScreen
import com.ebt.finance.features.admin.pengeluaran.presentation.state.PengeluaranState
import com.ebt.finance.features.admin.pengeluaran.presentation.viewmodel.PengeluaranViewModel
import com.ebt.finance.ui.theme.Accent
import com.ebt.finance.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAdmin(
    navController: NavController,
    viewModel: HomeAdminViewModel = hiltViewModel(),
    pemasukanViewModel: PemasukanViewModel,
    pengeluaranViewModel: PengeluaranViewModel,
    pemasukanState: GetPemasukanState,
    pengeluaranState: PengeluaranState
) {
    var tabIndex by remember {
        mutableStateOf(0)
    }
    val tabs = listOf("Pemasukan", "Pengeluaran")
    val icons = listOf(R.drawable.baseline_arrow_downward_24, R.drawable.baseline_arrow_upward_24)

    Scaffold(
        containerColor = Primary,
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Accent
                    ),
                    title = {
                        Column {
                            Text(
                                text = "Beranda",
                                color = Color.White,
                                fontWeight = FontWeight(600)
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                viewModel.logOut()
                                navController.navigate("auth_screen")
                            }
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = R.drawable.baseline_logout_24,
                                ),
                                contentDescription = "logout icon",
                                tint = Color.White
                            )
                        }
                    }
                )
                TabRow(
                    selectedTabIndex = tabIndex,
                    containerColor = Accent,
                    contentColor = Color.White,
                    indicator = {
                        Box(
                            modifier = Modifier
                                .tabIndicatorOffset(it[tabIndex])
                                .height(4.dp)
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        )
                    }
                ) {
                    tabs.forEachIndexed { index, s ->
                        Tab(
                            selected = tabIndex == index,
                            modifier = Modifier.padding(bottom = 16.dp),
                            onClick = {
                                tabIndex = index
                            }) {
                            Row {
                                Icon(
                                    painter = painterResource(
                                        id = icons[index]
                                    ),
                                    contentDescription = "untung"
                                )
                                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                                Text(
                                    text = s,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        },
        content = {
            Box(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
            ){
                when (tabIndex){
                    0 -> PemasukanScreen(
                        viewModel = pemasukanViewModel,
                        navController = navController,
                        state = pemasukanState
                    )
                    1 -> PengeluaranScreen(
                        navController,
                        viewModel = pengeluaranViewModel,
                        pengeluaranState = pengeluaranState,
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if(tabIndex == 0){
                        navController.navigate("tambah_data/pemasukan")
                    } else {
                        navController.navigate("tambah_data/pengeluaran")
                    }
                },
                containerColor = Accent,
                shape = CircleShape,
                content = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "add",
                        tint = Color.White
                    )
                }
            )
        }
    )

}