package com.example.moneyroute.utilities

import android.content.Context
import com.example.moneyroute.goals.data.Goal
import com.example.moneyroute.movements.data.Movement
import com.example.moneyroute.signup.data.User
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.UUID

class FirebaseManager(context: Context) {
    private val firestore = FirebaseFirestore.getInstance()

    private val auth = AuthManager(context)
    var userId = auth.getCurrentUser()?.uid

    suspend fun addUser(user: User) {
        user.id = UUID.randomUUID().toString()
        firestore.collection("users").document(user.id).set(user).await()
    }

    suspend fun updateUser(user: User) {
        val userRef = firestore.collection("users").document(user.id).set(user).await()
    }

    suspend fun deleteUser(userID: String) {
        val userRef = firestore.collection("users").document(userID)
        userRef.delete().await()
    }

    suspend fun addMovement(movement: Movement) {
        movement.id = UUID.randomUUID().toString()
        movement.userId = userId!!
        firestore.collection("movements").document(movement.id).set(movement)
    }

    suspend fun updateMovement(movement: Movement) {
        val movementRef = firestore.collection("movements").document(movement.id)
        movementRef.set(movement).await()
    }

    suspend fun deleteMovement(movementID: String) {
        val movementRef = firestore.collection("movements").document(movementID)
        movementRef.delete().await()
    }

    fun getMovementsFlow(prefix: String? = null, startDate: Long? = null, endDate: Long? = null) = callbackFlow {
        val movementRef = firestore.collection("movements")
            .whereGreaterThanOrEqualTo("date", startDate ?: 0)
            .whereLessThanOrEqualTo("date", endDate ?: Long.MAX_VALUE)

        val subscription = movementRef.addSnapshotListener { snapshot, _ ->
            snapshot?.let { querySnapshot ->
                val movements = mutableListOf<Movement>()
                for (document in querySnapshot.documents) {
                    val movement = document.toObject(Movement::class.java)
                    movement?.id = document.id
                    movement?.let {
                        if (prefix != null && movement.category.matches(regex = Regex("$prefix%", RegexOption.IGNORE_CASE))) {
                            movements.add(movement)
                        }
                    }
                }
                trySend(movements).isSuccess
            }
        }
        awaitClose { subscription.remove() }
    }

    suspend fun addGoal(goal: Goal) {
        goal.id = UUID.randomUUID().toString()
        goal.userId = userId!!
        firestore.collection("movements").document(goal.id).set(goal)
    }

    suspend fun updateGoal(goal: Goal) {
        val movementRef = firestore.collection("goals").document(goal.id)
        movementRef.set(goal).await()
    }

    suspend fun deleteGoal(goalId: String) {
        val movementRef = firestore.collection("goals").document(goalId)
        movementRef.delete().await()
    }

    suspend fun getGoals(prefix: String? = null, startDate: Long? = null, endDate: Long? = null): List<Goal> {
        val querySnapshot = firestore.collection("goals")
            .whereGreaterThanOrEqualTo("date", startDate ?: 0)
            .whereLessThanOrEqualTo("date", endDate ?: Long.MAX_VALUE).get().await()
        val goals = mutableListOf<Goal>()
        for (document in querySnapshot.documents) {
            val goal = document.toObject(Goal::class.java)!!
            if (prefix != null && goal.label.matches(regex = Regex("$prefix%", RegexOption.IGNORE_CASE))) {
                goals.add(goal)
            }
        }
        return goals
    }
}