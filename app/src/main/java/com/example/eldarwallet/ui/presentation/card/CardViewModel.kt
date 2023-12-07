package com.example.eldarwallet.ui.presentation.card

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.R
import com.example.eldarwallet.domain.Card
import com.example.eldarwallet.domain.User
import com.example.eldarwallet.domain.addCardUseCase
import com.example.eldarwallet.domain.getUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DEBUG_PROPERTY_NAME
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Base64
import java.util.Locale
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val addCardUseCase: addCardUseCase,
    private val getuserusecase: getUserUseCase,
):ViewModel() {
    private val _numCard = MutableLiveData<String>()
    val numCard: LiveData<String> = _numCard

    private val _imgCard = MutableLiveData<Int>()
    val imgCard: LiveData<Int> = _imgCard

    private val _monthCard = MutableLiveData<String>()
    val monthCard: LiveData<String> = _monthCard

    private val _yearCard = MutableLiveData<String>()
    val yearCard: LiveData<String> = _yearCard

    private val _codCard = MutableLiveData<String>()
    val codCard: LiveData<String> = _codCard

    private val _titularCard = MutableLiveData<String>()
    val titularCard: LiveData<String> = _titularCard

    private val _viewState = MutableStateFlow(CardUIState())
    val viewState: StateFlow<CardUIState> get() = _viewState

    private val _user = MutableLiveData<User>()
    val user: LiveData<User?> = _user

    private val _openDialog = MutableLiveData<Boolean>()
    val openDialog: LiveData<Boolean> = _openDialog

    /*Requerimiento:
    fun getImageResource(numCard: String)
    Las tarjetas asociadas podran ser Visa, Mastercard, American Express, etc.
    Detectar la marca de la tarjeta ingresada a partir del primer numero
    (3 Amex,4 Visa,5 Mastercad)
     */

    fun getImageResource(numCard: String): Int{
        return when {
            numCard.startsWith("3") -> R.drawable.ic_amex
            numCard.startsWith("4") -> R.drawable.ic_visa
            numCard.startsWith("5")-> R.drawable.ic_mastercard
            else -> R.drawable.ic_card
        }
    }

    fun updateNumCard(numCard: String) {
        _numCard.value = numCard
    }

    fun updateYearCard(yearCard: String) {
        _yearCard.value = yearCard
    }

    fun updateMonthCard(monthCard: String) {
        _monthCard.value = monthCard
    }

    fun updateCodCard(codCard: String) {
        _codCard.value = codCard
    }

    fun updateTitularCard(titularCard: String) {
        _titularCard.value = titularCard
    }

    fun getTitularString(): String? {
        val titular = _titularCard.value?.replace("\\s+".toRegex(), "")?.lowercase(Locale.ROOT)
        return titular
    }

    private fun getNamesUser(user: User): String? {
        val names = user.nombre+user.apellido
        val newname = names.replace("\\s+".toRegex(), "")?.lowercase(Locale.ROOT)
        return newname
    }

    fun setOpenDialog(value: Boolean) {
        _openDialog.value = value
    }

    /*  Requerimiento: Agregar nueva tarjeta.
        Se debera almacenar la informacion encriptada dentro de la base de datos interna.
     */

    //
    fun createCard() {
        viewModelScope.launch {
            try {
                _viewState.value = CardUIState(isLoading = true)
                val user = getuserusecase.invoke()
                val names = getNamesUser(user)
                val titular = getTitularString()
                if (names == titular){
                    val card = encryptCard()
                    addCardUseCase(card)
                    _viewState.value = CardUIState(isLoading = false,isCreated = true)
                }else{
                    Log.d("ERRORCARD", "NO SON IGUALES" )
                    _openDialog.value = true
                    _viewState.value = CardUIState(isError = true)
                }
                _user.value = user
            } catch (e: Exception) {
                Log.d("ERROR",e.localizedMessage)
            }
        }
    }
    //

    private fun encryptCard(): Card {
        val encryptionKey = "miClaveEldar1234"
        val encryptedCardNumber = encrypt(_numCard.value, encryptionKey)
        val encryptedExpiryMonthDate = encrypt(_monthCard.value, encryptionKey)
        val encryptedExpiryYearDate = encrypt(_yearCard.value, encryptionKey)
        val encryptedCvv = encrypt(_codCard.value, encryptionKey)
        val encryptedName = encrypt(_titularCard.value, encryptionKey)

       val card = Card(
           cardNumber = encryptedCardNumber,
           cardMonth = encryptedExpiryMonthDate,
           cardYear = encryptedExpiryYearDate,
           codSecuruty = encryptedCvv,
           cardTitular = encryptedName
       )
        return card
    }

    private fun encrypt(data: String?, key: String): String {
        val cipher = Cipher.getInstance("AES")
        val secretKey = SecretKeySpec(key.toByteArray(), "AES")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val encryptedData = cipher.doFinal(data?.toByteArray())
        return Base64.getEncoder().encodeToString(encryptedData)
    }

    fun clearFields() {
        _viewState.value = CardUIState(isCreated = false)
        _numCard.value = ""
        _monthCard.value = ""
        _yearCard.value = ""
        _codCard.value = ""
        _titularCard.value = ""

    }


}