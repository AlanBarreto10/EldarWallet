package com.example.eldarwallet

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eldarwallet.navigation.graphs.HomeNavGraph
import com.example.eldarwallet.ui.components.BottomNavigationBar
import com.example.eldarwallet.ui.presentation.card.CardViewModel
import com.example.eldarwallet.ui.presentation.home.HomeViewModel
import com.example.eldarwallet.ui.presentation.pay.PayQRViewModel
import com.example.eldarwallet.ui.presentation.pay_2.GenPayViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    homeViewModel: HomeViewModel,
    cardViewModel: CardViewModel,
    payqrViewModel : PayQRViewModel,
    genPayViewModel: GenPayViewModel,
    navController: NavHostController = rememberNavController())
{
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { padding ->
        HomeNavGraph(
            homeViewModel = homeViewModel,
            cardViewModel = cardViewModel,
            payqrViewModel = payqrViewModel,
            genPayViewModel =  genPayViewModel,
            navController = navController)
    }
}