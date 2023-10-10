package com.filippo.repos.database

import android.content.Context
import androidx.room.Room
import com.filippo.repos.database.schema.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            GithubDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideCommitsDao(database: GithubDatabase) = database.commitsDao()

    @Provides
    @Singleton
    fun provideRepositoriesDao(database: GithubDatabase) = database.repositoriesDao()
}
