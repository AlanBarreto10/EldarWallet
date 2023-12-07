package com.example.eldarwallet.ui.presentation.card


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eldarwallet.navigation.graphs.Graph
import com.example.eldarwallet.navigation.screens.BottomNavItem
import com.example.eldarwallet.ui.components.NewCard


@Composable
fun CardScreen(cardViewModel: CardViewModel, navController: NavController) {

    /* Requerimiento: Agregar nueva tarjeta */

    val viewState by cardViewModel.viewState.collectAsState()
    val openDialog by cardViewModel.openDialog.observeAsState(initial = false)

    if (viewState.isLoading) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(
                modifier = Modifier.size(100.dp).padding(16.dp)
            )
        }
    }else if (viewState.isCreated){
        LaunchedEffect(Unit){
            cardViewModel.clearFields()
            navController.navigate(BottomNavItem.Home.route) {}
        }
//        Column(modifier = Modifier.fillMaxSize().padding(top = 100.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            TextCardScreen()
//            Spacer(modifier = Modifier.padding(15.dp))
//            NewCard(cardViewModel)
//        }
    }else {
        if (openDialog) {
                AlertDialog(
                    icon = {
                        Icons.Filled.Info
                    },
                    title = {
                        Text(text = "Atención")
                    },
                    text = {
                        Text(text = "El titular no coincide con el nombre y apellido del usuario")
                    },
                    onDismissRequest = {
                        cardViewModel.setOpenDialog(false)
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                cardViewModel.setOpenDialog(false)
                                cardViewModel.clearFields()
                            }
                        ) {
                            Text("Aceptar")
                        }
                    },
                )
        } else {
            Column(modifier = Modifier.fillMaxSize().padding(top = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextCardScreen()
                Spacer(modifier = Modifier.padding(15.dp))
                NewCard(cardViewModel)
            }
        }
    }
}

@Composable
fun TextCardScreen() {
    Text(text = "Agregue aquí su nueva tarjeta",
        color = Color(0xFF1B92A2),
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )
}
