package com.example.eldarwallet.data.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class QrCodeRepository @Inject constructor(private val okHttpClient: OkHttpClient) {


    suspend fun generateQrCode(content: String): Bitmap? { //content es nombre y apellido
        Log.d("RepositoryQR:",  "entro")
        val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
        val body =
            "content=$content&width=128&height=128&fg-color=%23000000&bg-color=%23ffffff".toRequestBody(
                mediaType
            )
        Log.d("RepositoryQR:",  body.toString())
        val request = Request.Builder()
            .url("https://neutrinoapi-qr-code.p.rapidapi.com/qr-code")
            .post(body)
            .addHeader("content-type", "application/x-www-form-urlencoded")
            .addHeader("X-RapidAPI-Key", "6c8f03e7d7msh737f8441fff6bddp116d0cjsne33fff62c265")
            .addHeader("X-RapidAPI-Host", "neutrinoapi-qr-code.p.rapidapi.com")
            .build()

        Log.d("RepositoryQR:",  request.toString())

        okHttpClient.newCall(request).execute().use { response ->

            Log.d("RepositoryQR-ResponseCode", "Código de respuesta: ${response.code}")

            if (!response.isSuccessful){
                Log.d("RepositoryQR-Error", "Mensaje de error: ${response.message}")
                return null
            }

            val inputStream = response.body?.byteStream()
            return BitmapFactory.decodeStream(inputStream)
        }
    }


}