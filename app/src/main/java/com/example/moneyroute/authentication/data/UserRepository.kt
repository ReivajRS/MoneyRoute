package com.example.moneyroute.authentication.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class UserRepository
@Inject
constructor(
    @Named("users")
    private val userList: CollectionReference,
    private val firebaseAuth: FirebaseAuth
){
    suspend fun addUser(user: User) {
        try {
            userList.document(user.id).set(user).await()
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun createUserWithEmailAndPassword(email: String, password: String): AuthRes<FirebaseUser?> {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            AuthRes.Success(authResult.user)
        } catch(e: Exception) {
            AuthRes.Error(e.message ?: "Error al crear el usuario")
        }
    }

    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthRes<FirebaseUser?> {
        return try {
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            AuthRes.Success(authResult.user)
        } catch(e: Exception) {
            AuthRes.Error(e.message ?: "Error al iniciar sesión")
        }
    }

    fun getUserId(): String? = firebaseAuth.currentUser?.uid
}