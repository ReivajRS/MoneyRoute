package com.example.moneyroute.goals.data

import com.example.moneyroute.utilities.Result
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class GoalsRepository @Inject constructor(
    @Named("goals")
    private val goalsCollection: CollectionReference,
    @Named("contributions")
    private val contributionsCollection: CollectionReference
){
    suspend fun addGoal(goal: Goal) {
        try {
            goalsCollection.document(goal.id).set(goal).await()
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getGoalList(
        prefix: String,
        startDate: Long,
        endDate: Long,
        userId: String
    ): Flow<Result<List<Goal>>> = flow {
        try {
            emit(Result.Loading())

            val movementList1 = goalsCollection
                .whereEqualTo("userId", userId)
                .whereGreaterThanOrEqualTo("startDate", startDate)
                .whereLessThanOrEqualTo("startDate", endDate)
                .get().await().toObjects(Goal::class.java)

            val movementList2 = goalsCollection
                .whereEqualTo("userId", userId)
                .whereGreaterThanOrEqualTo("goalDate", startDate)
                .whereLessThanOrEqualTo("goalDate", endDate)
                .get().await().toObjects(Goal::class.java)

            val movementList = ((movementList1 + movementList2).toSet()).toList()

            emit(
                Result.Success(
                    data = movementList.filter { goal ->
                        goal.label.startsWith(prefix, ignoreCase = true)
                    }
                )
            )
        }
        catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: "Error desconocido"))
            e.printStackTrace()
        }
    }

    suspend fun deleteGoal(goalId: String) {
        try {
            contributionsCollection.whereEqualTo("goalId", goalId).get().await().documents.forEach {
                it.reference.delete().await()
            }
            goalsCollection.document(goalId).delete().await()
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun contributeGoal(contribution: Contribution) {
        try {
            val goal =
                goalsCollection.document(contribution.goalId).get().await().toObject(Goal::class.java) ?: return
            val currentAmount = goal.currentAmount
            val newAmount = currentAmount + contribution.amount
            val status = if (newAmount >= goal.goalAmount) Status.Achieved.status else goal.status

            contributionsCollection.document(contribution.id).set(contribution).await()
            goalsCollection.document(contribution.goalId)
                .update("currentAmount", newAmount, "status", status)
                .await()
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getContributionList(
        prefix: String,
        startDate: Long,
        endDate: Long,
        userId: String
    ): Flow<Result<List<Contribution>>> = flow {
        try {
            emit(Result.Loading())

            val goalList = goalsCollection
                .whereEqualTo("userId", userId)
                .get().await().toObjects(Goal::class.java)

            val contributionList = mutableListOf<Contribution>()
            for (goal in goalList) {
                contributionList.addAll(
                    contributionsCollection
                        .whereEqualTo("goalId", goal.id)
                        .whereGreaterThanOrEqualTo("date", startDate)
                        .whereLessThanOrEqualTo("date", endDate)
                        .get().await().toObjects(Contribution::class.java)
                )
            }

            emit(
                Result.Success(
                    data = contributionList.filter { contribution ->
                        contribution.goalLabel.startsWith(prefix, ignoreCase = true)
                    }
                )
            )
        }
        catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: "Error desconocido"))
            e.printStackTrace()
        }
    }

    suspend fun deleteContribution(contributionId: String, goalId: String) {
        try {
            val contribution = contributionsCollection.document(contributionId).get().await()
                .toObject(Contribution::class.java) ?: return
            contributionsCollection.document(contributionId).delete().await()
            val goal =
                goalsCollection.document(goalId).get().await().toObject(Goal::class.java) ?: return
            val isGoalAchieved = goal.currentAmount >= goal.goalAmount
            if (isGoalAchieved && System.currentTimeMillis() > goal.goalDate) {
                goalsCollection.document(goalId).update("status", Status.NotAchieved.status)
            }
            else {
                goalsCollection.document(goalId).update("status", Status.Active.status)
            }
            goalsCollection.document(goalId).update("currentAmount", goal.currentAmount - contribution.amount)
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }
}