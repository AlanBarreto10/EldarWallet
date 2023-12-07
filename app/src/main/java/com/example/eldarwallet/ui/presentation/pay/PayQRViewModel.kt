package com.example.eldarwallet.ui.presentation.pay

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.domain.User
import com.example.eldarwallet.domain.getQrCodeUseCase
import com.example.eldarwallet.domain.getUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PayQRViewModel @Inject constructor(
    private val getQrCodeUseCase: getQrCodeUseCase,
    private val getuserusecase: getUserUseCase,
): ViewModel(){

    private val _qr = MutableLiveData<Bitmap?>()
    val qr: LiveData<Bitmap?> = _qr

    private val _viewState = MutableStateFlow(PayUIState())
    val viewState: StateFlow<PayUIState> get() = _viewState


    /* Requerimiento: Pago con QR.Generar un QR con el nombre y apellido
       del usuario utilizando la siguiente api https://rapidapi.com/neutrinoapi/api/qr-code/
       y presentar resultado en pantalla */

    //
    fun init(){
        viewModelScope.launch {
            _viewState.value = PayUIState(isLoading = true)
            val user = getuserusecase.invoke() //obtengo nombre y apellido del usuario
            val names = getNamesUser(user)     //merge en un solo String
            val bitmap = getQrCodeUseCase(names.toString()) //funcion caso de uso
            if(bitmap != null){
                _qr.value = bitmap //guardo valor
                _viewState.value = PayUIState(isLoading = false, isGenerated = true) //actualizo state de screen
            }else{
                Log.d("ERROR", "No se pudo obtener el bitmap")
            }
        }
    }
    //

    private fun getNamesUser(user: User): String? {
        val names = user.nombre+user.apellido
        val newname = names.replace("\\s+".toRegex(), "")?.lowercase(Locale.ROOT)
        return newname
    }

}