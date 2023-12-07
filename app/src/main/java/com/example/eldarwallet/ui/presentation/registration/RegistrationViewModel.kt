package com.example.eldarwallet.ui.presentation.registration

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.domain.RegisterUseCase
import com.example.eldarwallet.domain.User
import com.example.eldarwallet.domain.addUserUseCase
import com.example.eldarwallet.domain.deleteCardsUseCase
import com.example.eldarwallet.domain.deleteUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val addUserUseCase: addUserUseCase,
    private val deleteCardsUseCase: deleteCardsUseCase,
    private val deleteUserUseCase: deleteUserUseCase,

): ViewModel() {

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _firstname = MutableLiveData<String>()
    val firstname : LiveData<String> = _firstname

    private val _secondname = MutableLiveData<String>()
    val secondname : LiveData<String> = _secondname


    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password


    private val _repeatpassword = MutableLiveData<String>()
    val repeatpassword : LiveData<String> = _repeatpassword

    private val _viewState = MutableStateFlow(RegistrationUIState())
    val viewState: StateFlow<RegistrationUIState> get() = _viewState


    fun onEmailChanged(email: String){
        _email.value = email
    }

    fun onPasswordChanged(password: String){
        _password.value = password
    }

    fun onFirstNameChanged(firstname: String){
        _firstname.value = firstname
    }

    fun onSecondNameChanged(secondname: String){
        _secondname.value = secondname
    }

    fun onRepeatPasswordChanged(repeatpassword: String){
        _repeatpassword.value = repeatpassword
    }


    fun onSignInSelected(email: String, nombre: String, apellido: String, password: String) {
        val user = User(0, email, nombre, apellido, password)
        signInUser(user)

    }

    private fun signInUser(user: User) {
        viewModelScope.launch {
            val result = registerUseCase(user) //agrego a firebase
            if(result){
                deleteCardsUseCase.invoke()
                deleteUserUseCase.invoke()
                addUserUseCase(user)
                _viewState.value = RegistrationUIState(isLoading = false, isRegister = true)
            }else{
                _viewState.value = RegistrationUIState(isLoading = false, isError = true)
            }
        }
    }


}