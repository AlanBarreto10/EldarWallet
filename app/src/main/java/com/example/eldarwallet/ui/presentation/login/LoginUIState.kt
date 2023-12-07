package com.example.eldarwallet.ui.presentation.login

data class LoginUIState(
    val isLoading: Boolean = false,
    val isValidEmail: Boolean = true,
    val isValidPassword: Boolean = true,
    val isLogIn: Boolean = false
)
