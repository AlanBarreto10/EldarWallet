package com.example.eldarwallet.ui.presentation.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.domain.Card
import com.example.eldarwallet.domain.getCardsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCardsUseCase: getCardsUseCase,
):ViewModel() {

    private val _cards = mutableStateListOf<Card>()
    val cards: List<Card> = _cards

    private val _viewState: MutableState<HomeUIState> = mutableStateOf(HomeUIState())
    val viewState: State<HomeUIState> get() = _viewState


    fun init() {
        _cards.clear()
        viewModelScope.launch {
            _viewState.value = HomeUIState(isLoading = true)
            try {
                val result = getCardsUseCase()
                if(result.isNotEmpty()){
                    result.forEach { card ->
                        val card = decryptCard(card)
                        _cards.add(card)
                    }
                }

                _viewState.value = HomeUIState(isLoading = false, cards = _cards)
            }catch (e: Exception){
                Log.d("Error", e.localizedMessage)
            }

        }
    }

    private fun decryptCard(card: Card): Card {
        val encryptionKey = "miClaveEldar1234"
        val descrypyNumber = decrypt(card.cardNumber, encryptionKey)

        val card = Card(
            cardNumber = descrypyNumber,
            cardMonth = card.cardMonth,
            cardYear = card.cardYear,
            codSecuruty = card.codSecuruty,
            cardTitular = card.cardTitular
        )
        return card
    }

    private fun decrypt(data: String?, key: String): String {
        val cipher = Cipher.getInstance("AES")
        val secretKey = SecretKeySpec(key.toByteArray(), "AES")
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        val decodedData = Base64.getDecoder().decode(data)
        val decryptedData = cipher.doFinal(decodedData)
        return String(decryptedData)
    }

    fun getTypeCard(cardNumber: String): String {
        return when {
            cardNumber.startsWith("3") -> "AMEX"
            cardNumber.startsWith("4") -> "VISA"
            cardNumber.startsWith("5")-> "MASTERCARD"
            else -> "OTRO"
        }
    }

}