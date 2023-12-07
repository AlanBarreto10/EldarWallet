package com.example.eldarwallet.ui.presentation.pay

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eldarwallet.R

@Composable
fun PayQRScreen(payQRViewModel: PayQRViewModel){
    val viewState by payQRViewModel.viewState.collectAsState()
    val qrBitmap by payQRViewModel.qr.observeAsState()

    LaunchedEffect(Unit) {
        payQRViewModel.init()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextQrScreen()
        if(viewState.isLoading){
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(16.dp)
                )
            }
        }
        if(viewState.isGenerated){
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                /* "You have exceeded the DAILY quota for requests on your current plan, BASIC.
                    Upgrade your plan at https://rapidapi.com/neutrinoapi/api/qr-code */

                /* Or Second code response = 429, not access */
                QrCodeImage(bitmap = qrBitmap)

            }
        }

    }
}
@Composable
fun QrCodeImage(bitmap: Bitmap?) {
    bitmap?.let {
        val imageBitmap = it.asImageBitmap()
        Image(bitmap = imageBitmap, contentDescription = "QR Code")
    }
}

@Composable
fun TextQrScreen() {
    Text(text = "Tu QR",
        color = Color(0xFF1B92A2),
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp)
}