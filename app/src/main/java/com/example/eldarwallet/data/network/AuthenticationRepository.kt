package com.example.eldarwallet.data.network

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(private val firebase: FirebaseClient) {
    //esta funcion me devuelve un LoginResult (response) y luego utilizo una funcion extension para mapear resultados
    suspend fun login(email: String, password: String): LoginResult = runCatching {
        firebase.auth.signInWithEmailAndPassword(email, password).await()
    }.toLoginResult()


    suspend fun createAccount(email: String, password: String): AuthResult? {
        return firebase.auth.createUserWithEmailAndPassword(email, password).await()
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebase.currentUser
    }

    fun logout() {
        firebase.auth.signOut()
    }

    private fun Result<AuthResult>.toLoginResult() = when (val result = getOrNull()) {
        null -> LoginResult.Error
        else -> {
            val userId = result?.user
            checkNotNull(userId)
            LoginResult.Success(result.user?.isEmailVerified ?: false)
        }
    }

}

sealed class LoginResult {
    object Error : LoginResult()
    data class Success(val verified: Boolean) : LoginResult()
}