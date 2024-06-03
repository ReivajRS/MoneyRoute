package com.example.moneyroute.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideFirebaseInstance() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseAuthInstance() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    @Named("users")
    fun provideUserList(
        db: FirebaseFirestore
    ) = db.collection("users")

    @Provides
    @Singleton
    @Named("movements")
    fun provideMovementList(
        db: FirebaseFirestore
    ) = db.collection("movements")

    @Provides
    @Singleton
    @Named("goals")
    fun provideGoalList(
        db: FirebaseFirestore
    ) = db.collection("goals")

    @Provides
    @Singleton
    @Named("contributions")
    fun provideContributionList(
        db: FirebaseFirestore
    ) = db.collection("contributions")
}