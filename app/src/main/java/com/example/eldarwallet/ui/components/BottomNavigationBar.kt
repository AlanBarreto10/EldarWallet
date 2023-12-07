package com.example.eldarwallet.ui.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.eldarwallet.navigation.screens.BottomNavItem

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {
    val screens = listOf(
        BottomNavItem.Home,
        BottomNavItem.Card,
        BottomNavItem.PayQR,
        BottomNavItem.PayQR_2
    )

    val currentRoute = currentRoute(navController)

    BottomAppBar(
        containerColor = Color(0xFFCCCCCC),
        contentColor = Color(0xFF1B92A2)
    ) {
        NavigationBar() {
            screens.forEach{ item->
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            launchSingleTop = true
                        }

                    },
                    icon = {
                        Icon(
                            contentDescription = null,
                            imageVector = item.icon,
                            tint = Color(0xFF1B92A2)
                        )
                    })
            }
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}