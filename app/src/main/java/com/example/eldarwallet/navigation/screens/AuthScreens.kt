package com.example.eldarwallet.navigation.screens

sealed class AuthScreen( val route : String ){
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
}