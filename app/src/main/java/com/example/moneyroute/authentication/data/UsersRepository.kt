package com.example.moneyroute.authentication.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await
import com.example.moneyroute.utilities.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class UsersRepository
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

    fun getUserAccount(userId: String): Flow<Result<User>> = flow {
        try {
            emit(Result.Loading())
            val userAccount = userList.document(userId).get().await().toObject(User::class.java)
            emit(Result.Success(userAccount))
        }
        catch (e: Exception) {
            emit(Result.Error(message = e.message ?: "Error al obtener la cuenta de usuario"))
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

    fun signOut() {
        firebaseAuth.signOut()
    }

    fun getUserId(): String? = firebaseAuth.currentUser?.uid
}