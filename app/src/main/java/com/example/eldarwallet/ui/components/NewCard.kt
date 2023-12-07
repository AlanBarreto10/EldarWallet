package com.example.eldarwallet.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eldarwallet.ui.presentation.card.CardViewModel

@Composable
fun NewCard(cardViewModel: CardViewModel) {
    val numCard:String by cardViewModel.numCard.observeAsState(initial = "")
    val codCard:String by cardViewModel.codCard.observeAsState(initial = "")
    val titularCard:String by cardViewModel.titularCard.observeAsState(initial = "")

    Card(
        Modifier.padding(10.dp).width(350.dp),
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
            containerColor = Color.White,
            disabledContentColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column {
            Row(
                Modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(text = "Ingresar nueva tarjeta",
                        color = Color(0xFF1B92A2),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = cardViewModel.getImageResource(numCard)),
                            contentDescription = "type card",
                            modifier = Modifier.width(55.dp)
                        )
                        TextField(
                            value = numCard,
                            onValueChange = { cardViewModel.updateNumCard(it)},
                            label = { Text("Numero de tarjeta", color = Color(0xFF1B92A2)) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),//2
                            singleLine = true,
                            maxLines = 1,
                        )
                    }
                }
            }
            Row(
                Modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier
                    .width(150.dp)) {
                    Text(text = "Fecha de vencimiento",
                        color = Color(0xFF1B92A2),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.padding(1.dp))
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center
                    ) {
                        MesMenuDown(cardViewModel)
                        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                        YearMenuDown(cardViewModel)
                    }
                }

                Spacer(modifier = Modifier.padding(horizontal = 10.dp))

                Column(modifier = Modifier
                    .width(150.dp)) {
                    Text(text = "Codigo de seguridad",
                        color = Color(0xFF1B92A2),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp)
                    Spacer(modifier = Modifier.padding(5.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TextField(
                            value = codCard,
                            onValueChange = { cardViewModel.updateCodCard(it) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),//2
                            singleLine = true,
                            maxLines = 1,
                        )
                    }
                }
            }
            Row(
                Modifier.fillMaxWidth().padding(10.dp)
            ) {
                TextField(
                    value = titularCard,
                    onValueChange = { cardViewModel.updateTitularCard(it)},
                    label = { Text("Nombre y Apellido", color = Color(0xFF1B92A2)) },
                    singleLine = true,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = { cardViewModel.createCard() } ,
                    enabled = true,
                    modifier = Modifier.width(250.dp).height(40.dp).padding(horizontal = 5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1B92A2),
                        disabledContentColor = Color(0xFFCCCCCC)
                    )
                ) {
                    Text(text = "Agregar tarjeta",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
            }
        }

    }
}

@Composable
fun YearMenuDown(cardViewModel: CardViewModel) {
    val yearCard:String by cardViewModel.yearCard.observeAsState(initial = "")

    Column(Modifier.width(60.dp)) {
        OutlinedTextField(
            value = yearCard,
            onValueChange = { cardViewModel.updateYearCard(it)},
            label = { Text(text = "Año", color = Color.Gray, fontSize = 12.sp)},
            enabled = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun MesMenuDown(cardViewModel: CardViewModel) {
    val monthCard:String by cardViewModel.monthCard.observeAsState(initial = "")

        Column(Modifier.width(60.dp)) {
            OutlinedTextField(
                value = monthCard,
                onValueChange = { cardViewModel.updateMonthCard(it)},
                label = { Text(text = "Mes", color = Color.Gray, fontSize = 12.sp)},
                enabled = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

}
