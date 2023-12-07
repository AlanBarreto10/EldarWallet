package com.example.eldarwallet.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.eldarwallet.R

@Composable
fun BackgroudImage() {
    Image(painter = painterResource(id = R.drawable.background_img),
        contentDescription = "ciudad fondo 1",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop)
}