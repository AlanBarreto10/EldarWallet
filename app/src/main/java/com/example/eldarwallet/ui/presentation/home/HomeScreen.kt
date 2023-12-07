package com.example.eldarwallet.ui.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eldarwallet.ui.components.Cards
import com.example.eldarwallet.ui.components.MyWallet

@Composable
fun HomeScreen(homeViewModel: HomeViewModel, navController: NavController){

    /* Requerimiento: Pantalla principal: Saldo, listado de tarjetas asociadas, botones de acceso a otras pantallas.
       Extra: registrar nuevo usuario */

    val viewState = homeViewModel.viewState.value
    val cards = homeViewModel.cards

    LaunchedEffect(Unit) {
        homeViewModel.init()
    }

    if(cards.isNotEmpty()){
        Column(
            modifier = Modifier.fillMaxSize().padding(10.dp)
        ) {
            MyWallet()
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Mis tarjetas",
                    fontSize = 20.sp,
                    color = Color(0xFF1B92A2)
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Cards(viewState.cards, homeViewModel, navController )
        }
    }else if(viewState.isLoading) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(
                modifier = Modifier.size(100.dp).padding(16.dp)
            )
        }
    }else {
        Column(
            modifier = Modifier.fillMaxSize().padding(10.dp)
        ) {
            MyWallet()
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Mis tarjetas",
                    fontSize = 20.sp,
                    color = Color(0xFF1B92A2)
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }


}