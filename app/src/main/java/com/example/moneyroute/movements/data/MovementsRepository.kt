package com.example.moneyroute.movements.data

import com.example.moneyroute.utilities.Result
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class MovementsRepository @Inject constructor(
    @Named("movements")
    private val movementsCollection: CollectionReference
) {
    suspend fun addMovement(movement: Movement) {
        try {
            movementsCollection.document(movement.id).set(movement).await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getMovementList(prefix: String, startDate: Long, endDate: Long, userId: String): Flow<Result<List<Movement>>> = flow {
        try {
            emit(Result.Loading())

            val movementList = movementsCollection
                .whereEqualTo("userId", userId)
                .whereGreaterThanOrEqualTo("date", startDate)
                .whereLessThanOrEqualTo("date", endDate)
                .get().await().toObjects(Movement::class.java)

            emit(
                Result.Success(
                    data = movementList.filter { movement ->
                        movement.category.startsWith(prefix, ignoreCase = true)
                    }
                )
            )
        }
        catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: "Error desconocido"))
        }
    }

    suspend fun deleteMovement(movementId: String) {
        try {
            movementsCollection.document(movementId).delete().await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}