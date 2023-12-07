package com.example.eldarwallet.data.network

import android.util.Log
import com.example.eldarwallet.data.database.dao.UserDao
import com.example.eldarwallet.data.database.entity.UserEntity
import com.example.eldarwallet.domain.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val firebase: FirebaseClient
) {
    companion object {
        const val USER_COLLECTION = "users"
    }

    suspend fun getCurrentUserFromFirestore(): User? {
        val userid = firebase.auth.currentUser?.uid
        if (userid != null) {
            try {
                val querySnapshot = firebase.db.collection(USER_COLLECTION)
                    .whereEqualTo("user_id", userid)
                    .limit(1)
                    .get()
                    .await()

                if (!querySnapshot.isEmpty) {
                    val documentSnapshot = querySnapshot.documents.first() // Obtiene el primer documento
                    val userData = documentSnapshot.data // Todos los datos como un mapa
                    if (userData != null) {
                        val email = userData["email"] as String
                        val nombre = userData["nombre"] as String
                        val apellido = userData["apellido"] as String
                        val contrasena = userData["apellido"] as String

                        return User(0, email, nombre, apellido, contrasena)

                        Log.i("firebase", "Nombre: $nombre, Apellido: $apellido, Email: $email")
                    }
                } else {
                    Log.i("firebase", "No document found for user ID: $userid")
                }
            } catch (e: Exception) {
                Log.e("firebase", "Error searching for user document", e)
            }
        } else {
            Log.i("firebase", "No user is currently signed in")
        }
        return null
    }


    suspend fun createUserTableInFb(user: User) = runCatching {

        val user = hashMapOf(
            "user_id" to firebase.auth.currentUser?.uid,
            "email" to user.email,
            "nombre" to user.nombre,
            "apellido" to user.apellido,
            "password" to user.password
        )
        val documentReference = firebase.db
            .collection(USER_COLLECTION)
            .add(user).await()


    }.onFailure {
        Log.e("RegistroService", "Error al crear usuario en la tabla")
    }.isSuccess //verdadero pudo crear la cuenta, sino false
}