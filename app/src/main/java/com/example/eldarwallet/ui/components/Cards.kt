package com.example.eldarwallet.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eldarwallet.R
import com.example.eldarwallet.domain.Card
import com.example.eldarwallet.navigation.screens.BottomNavItem
import com.example.eldarwallet.ui.presentation.home.HomeViewModel


@Composable
fun Cards(cards: List<Card>, homeViewModel: HomeViewModel, navController: NavController) {

     LazyColumn() {
         items(cards.size) { index ->
             Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                 CardItem(index, cards, homeViewModel, navController)
             }

         }
     }

}

@Composable
fun CardItem(
    index: Int,
    cards: List<Card>,
    homeViewModel: HomeViewModel,
    navController: NavController
) {
    val card = cards[index]

    var image = painterResource(id = R.drawable.ic_mastercard)
    var color = Color.Black

    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp)
    ) {
        val type = homeViewModel.getTypeCard(card.cardNumber)

        when (type) {
            "VISA" -> image = painterResource(id = R.drawable.ic_visa)
            "AMEX" -> image = painterResource(id = R.drawable.ic_amex)
            "MASTERCARD" -> image = painterResource(id = R.drawable.ic_mastercard)
            "OTRO" -> image = painterResource(id = R.drawable.ic_card)
        }

        when (type) {
            "MASTERCARD" -> color = Color.Black
            "VISA" -> color = Color.DarkGray
            "AMEX" -> color = Color.Gray
            "OTRO" -> color = Color.Magenta
        }

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(color)
                .width(250.dp)
                .height(160.dp)
                .clickable { navController.navigate("pay_2/${card.cardNumber}")}
                .padding(10.dp)
        ) {

            Image(
                painter = image,
                contentDescription = "type card",
                modifier = Modifier.width(60.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = card.cardNumber,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

        }
    }
}
