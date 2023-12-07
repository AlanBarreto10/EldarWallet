package com.example.eldarwallet.di

import android.content.Context
import androidx.room.Room
import com.example.eldarwallet.data.database.CardDatabase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
       //Firebase
        @Provides
        fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

        //Room
        private val CARD_DATABASE_NAME = "card_database"

        @Singleton
        @Provides
        fun provideRoom(@ApplicationContext context: Context) =
                Room.databaseBuilder(context, CardDatabase::class.java, CARD_DATABASE_NAME).build()


        @Singleton
        @Provides
        fun provideCardDao(db: CardDatabase) = db.getCardDao()

       @Singleton
       @Provides
       fun provideUserDao(db: CardDatabase) = db.getUserDao()

        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .build()
        }


}