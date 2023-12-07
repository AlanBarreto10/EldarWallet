package com.example.eldarwallet.ui.presentation.pay_2


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eldarwallet.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenPayViewModel @Inject constructor(): ViewModel(){

    private val _numCard = MutableLiveData<String>()
    val numCard: LiveData<String> = _numCard

    private val _monto = MutableLiveData<String>()
    val monto: LiveData<String> = _monto

    private val _isEnabled = MutableLiveData<Boolean>()
    val isEnabled : LiveData<Boolean> = _isEnabled

    fun updateMonto(monto: String){
        _monto.value = monto
    }

    fun getImageResource(numCard: String): Int{
        return when {
            numCard.startsWith("3") -> R.drawable.ic_amex
            numCard.startsWith("4") -> R.drawable.ic_visa
            numCard.startsWith("5")-> R.drawable.ic_mastercard
            else -> R.drawable.ic_card
        }
    }

    fun init(cardNumber: String) {
        _monto.value = " "
        _numCard.value = " "
        _isEnabled.value = false
        if(cardNumber.equals("{cardNumber}")){
            _isEnabled.value = false
        }else{
            _numCard.value = cardNumber
            _isEnabled.value = true
        }
    }



}