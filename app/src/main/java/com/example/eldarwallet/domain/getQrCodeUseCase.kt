package com.example.eldarwallet.domain

import android.graphics.Bitmap
import android.util.Log
import com.example.eldarwallet.data.network.QrCodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class getQrCodeUseCase @Inject constructor(private val qrCodeRepository: QrCodeRepository) {

//    suspend operator fun invoke(content: String): Bitmap? = withContext(Dispatchers.IO) {
//        qrCodeRepository.generateQrCode(content)
//    }


    suspend operator fun invoke(content: String): Bitmap? = withContext(Dispatchers.IO) {
        qrCodeRepository.generateQrCode(content)
    }
}