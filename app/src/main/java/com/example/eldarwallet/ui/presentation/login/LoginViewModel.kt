package com.example.eldarwallet.ui.presentation.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.data.network.LoginResult
import com.example.eldarwallet.domain.LogInUseCase
import com.example.eldarwallet.domain.addUserUseCase
import com.example.eldarwallet.domain.deleteCardsUseCase
import com.example.eldarwallet.domain.deleteUserUseCase
import com.example.eldarwallet.domain.getCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val loginUseCase: LogInUseCase,
    private val addUserUseCase: addUserUseCase,
    private val getCurrentUserUseCase: getCurrentUserUseCase,
    private val deleteCardsUseCase: deleteCardsUseCase,
    private val deleteUserUseCase: deleteUserUseCase,
): ViewModel() {

    private companion object {
        const val MIN_PASSWORD_LENGTH = 6
    }

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password


    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable : LiveData<Boolean> = _isLoginEnable

    private val _viewState = MutableStateFlow(LoginUIState())
    val viewState: StateFlow<LoginUIState> get() = _viewState


    private val _openDialog = MutableLiveData<Boolean>()
    val openDialog: LiveData<Boolean> = _openDialog


    fun onLoginChanged(email: String, password: String){
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enableLogin(email,password)
    }

    private fun enableLogin(email: String, password: String) =
        isValidPassword(email) && isValidPassword(password)

    private fun isValidEmail(email: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean {
        return password.length >= MIN_PASSWORD_LENGTH
    }

    fun setOpenDialog(value: Boolean) {
        _openDialog.value = value
    }

    fun onLoginSelected(email: String, password: String) {
        val emailIsValid = isValidEmail(email)
        val passwordIsValid = isValidPassword(password)

        _viewState.value = LoginUIState(isLoading = true, isValidEmail = emailIsValid, isValidPassword = passwordIsValid)

        if(emailIsValid && passwordIsValid){
            viewModelScope.launch {
                _viewState.value = LoginUIState(isLoading = true)

                val result = loginUseCase(email, password)

                _viewState.value = when (result) {
                    is LoginResult.Success -> {
                        deleteCardsUseCase.invoke()
                        deleteUserUseCase.invoke()
                        val user = getCurrentUserUseCase.invoke()
                        if(user != null) addUserUseCase(user)
                        LoginUIState(isLoading = false, isValidEmail = true, isValidPassword = true, isLogIn = true)
                    }
                    is LoginResult.Error -> {
                        _openDialog.value = true
                        LoginUIState(isLoading = false, isValidEmail = false, isValidPassword = false, isLogIn = false)
                    }

                }
            }
        }else{
           _viewState.value = LoginUIState(isLoading = false, isValidEmail = false, isValidPassword = false)
        }

    }

}