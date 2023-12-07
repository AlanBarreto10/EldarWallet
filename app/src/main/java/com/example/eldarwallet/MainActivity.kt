package com.example.eldarwallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.eldarwallet.navigation.graphs.Graph
import com.example.eldarwallet.navigation.graphs.RootNavigationGraph
import com.example.eldarwallet.ui.components.NewCard
import com.example.eldarwallet.ui.presentation.card.CardScreen
import com.example.eldarwallet.ui.presentation.card.CardViewModel
import com.example.eldarwallet.ui.presentation.home.HomeScreen
import com.example.eldarwallet.ui.presentation.home.HomeViewModel
import com.example.eldarwallet.ui.presentation.login.LoginViewModel
import com.example.eldarwallet.ui.presentation.pay.PayQRViewModel
import com.example.eldarwallet.ui.presentation.pay_2.GenPayViewModel
import com.example.eldarwallet.ui.presentation.registration.RegistrationViewModel
import com.example.eldarwallet.ui.theme.EldarWalletTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val registrationViewModel by viewModels<RegistrationViewModel>()
    private val loginViewModel by viewModels<LoginViewModel>()
    private val homeViewModel by viewModels<HomeViewModel>()
    private val cardViewModel by viewModels<CardViewModel>()
    private val payQRViewModel by viewModels<PayQRViewModel>()
    private val genPayViewModel by viewModels<GenPayViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EldarWalletTheme {
                RootNavigationGraph(
                    loginViewModel = loginViewModel,
                    registrationViewModel = registrationViewModel,
                    homeViewModel = homeViewModel,
                    cardViewModel = cardViewModel,
                    payqrViewModel = payQRViewModel,
                    genPayViewModel = genPayViewModel,
                    startdestination = Graph.AUTHENTICATION,
                    navController = rememberNavController()
                )
            }
        }
    }
}

