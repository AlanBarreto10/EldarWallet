package com.example.eldarwallet.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eldarwallet.MainScreen
import com.example.eldarwallet.ui.presentation.card.CardViewModel
import com.example.eldarwallet.ui.presentation.home.HomeViewModel
import com.example.eldarwallet.ui.presentation.login.LoginViewModel
import com.example.eldarwallet.ui.presentation.pay.PayQRViewModel
import com.example.eldarwallet.ui.presentation.pay_2.GenPayViewModel
import com.example.eldarwallet.ui.presentation.registration.RegistrationViewModel

@Composable
fun RootNavigationGraph(
    loginViewModel: LoginViewModel,
    registrationViewModel: RegistrationViewModel,
    homeViewModel: HomeViewModel,
    cardViewModel: CardViewModel,
    payqrViewModel: PayQRViewModel,
    genPayViewModel: GenPayViewModel,
    startdestination: String,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = startdestination
    ) {
        authNavGraph(loginViewModel,registrationViewModel, navController = navController)
        composable(route = Graph.HOME) {
            MainScreen(homeViewModel, cardViewModel, payqrViewModel,genPayViewModel)
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
}