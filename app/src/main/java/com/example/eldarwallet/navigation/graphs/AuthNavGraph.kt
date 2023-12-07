package com.example.eldarwallet.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.eldarwallet.navigation.screens.AuthScreen
import com.example.eldarwallet.ui.presentation.login.LoginScreen
import com.example.eldarwallet.ui.presentation.login.LoginViewModel
import com.example.eldarwallet.ui.presentation.registration.RegistrationScreen
import com.example.eldarwallet.ui.presentation.registration.RegistrationViewModel

fun NavGraphBuilder.authNavGraph(
    loginViewModel: LoginViewModel,
    registerViewModel: RegistrationViewModel,
    navController: NavHostController
){
    navigation(
        startDestination = AuthScreen.Login.route,
        route = Graph.AUTHENTICATION
    ){

        composable(
            route = AuthScreen.Login.route
        ){
            LoginScreen(loginViewModel, navController = navController)
        }

        composable(
            route = AuthScreen.SignUp.route
        ){
            RegistrationScreen(registerViewModel,navController = navController)
        }
    }
}