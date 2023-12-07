package com.example.eldarwallet.ui.presentation.pay_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eldarwallet.navigation.screens.BottomNavItem

@Composable
fun GenPayScren(navController: NavController,genPayViewModel: GenPayViewModel, cardNumber: String){

    /* Requerimiento: generar un pago, seleccionando SI O SI una tarjeta asociada */
    /* La implementacion del NFC no es necesario, por lo que el monto ingresado es irrelevante*/

    val monto by genPayViewModel.monto.observeAsState(initial = "")
    val isEnabled:Boolean by genPayViewModel.isEnabled.observeAsState(initial = false)

    genPayViewModel.init(cardNumber)

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier.padding(horizontal = 40.dp),
            value = monto,
            onValueChange = { genPayViewModel.updateMonto(monto = it) },
            enabled = isEnabled,
            label = { Text("Ingrese monto", color =  Color(0xFF1B92A2)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFF1B92A2),
                focusedIndicatorColor = Color(0xFF1B92A2),
                unfocusedIndicatorColor = Color(0xFF1B92A2)
            )
        )
        Spacer(modifier = Modifier.padding(10.dp))
        CardSelected(genPayViewModel)
        Spacer(modifier = Modifier.padding(25.dp))
        Button(onClick = { navController.navigate(BottomNavItem.Home.route)} ,
            modifier = Modifier
                .height(58.dp)
                .padding(horizontal = 5.dp),
            colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF00BCD4),
            ),
            enabled = isEnabled
        ) {
            Text(text = "Pagar",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp)

        }


    }

}

@Composable
fun CardSelected(genPayViewModel: GenPayViewModel) {
    val numCard:String by genPayViewModel.numCard.observeAsState(initial = "")

    Card(
        Modifier
            .padding(10.dp)
            .width(200.dp)
            .height(400.dp),
             colors = CardDefaults.cardColors(
            contentColor = Color.DarkGray,
            containerColor = Color.DarkGray
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = genPayViewModel.getImageResource(numCard)),
                contentDescription = "type card",
                modifier = Modifier.width(100.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = numCard,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }
}
