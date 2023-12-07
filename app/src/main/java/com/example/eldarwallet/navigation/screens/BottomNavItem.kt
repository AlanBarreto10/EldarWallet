package com.example.eldarwallet.navigation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCard
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector) {
    object Home : BottomNavItem("home", Icons.Default.Home)
    object Card : BottomNavItem("card", Icons.Default.AddCard)
    object PayQR : BottomNavItem("pay_qr", Icons.Default.QrCode)
    object PayQR_2 : BottomNavItem("pay_2/{cardNumber}", Icons.Default.Payments)
}
