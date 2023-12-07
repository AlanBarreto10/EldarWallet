package com.example.eldarwallet.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.eldarwallet.navigation.screens.BottomNavItem
import com.example.eldarwallet.ui.presentation.card.CardScreen
import com.example.eldarwallet.ui.presentation.card.CardViewModel
import com.example.eldarwallet.ui.presentation.home.HomeScreen
import com.example.eldarwallet.ui.presentation.home.HomeViewModel
import com.example.eldarwallet.ui.presentation.pay.PayQRScreen
import com.example.eldarwallet.ui.presentation.pay.PayQRViewModel
import com.example.eldarwallet.ui.presentation.pay_2.GenPayScren
import com.example.eldarwallet.ui.presentation.pay_2.GenPayViewModel


@Composable
fun HomeNavGraph(
    homeViewModel: HomeViewModel,
    cardViewModel: CardViewModel,
    payqrViewModel: PayQRViewModel,
    genPayViewModel: GenPayViewModel,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(homeViewModel, navController)
        }

        composable(BottomNavItem.Card.route) {
            CardScreen(cardViewModel, navController)
        }

        composable(BottomNavItem.PayQR.route) {
            PayQRScreen(payqrViewModel)
        }

        composable(
            route = BottomNavItem.PayQR_2.route,
            arguments = listOf(navArgument("cardNumber") { type = NavType.StringType })
        ) { backStackEntry ->
            val cardNumber = backStackEntry.arguments?.getString("cardNumber") ?: ""
            GenPayScren(navController, genPayViewModel, cardNumber)
        }

    }
}