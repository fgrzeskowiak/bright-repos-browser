package com.filippo.repos.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.filippo.repos.database.dao.CommitsDao
import com.filippo.repos.database.dao.RepositoriesDao
import com.filippo.repos.database.model.CommitEntity
import com.filippo.repos.database.model.RepositoryEntity

@Database(entities = [RepositoryEntity::class, CommitEntity::class], version = 1)
internal abstract class GithubDatabase : RoomDatabase() {
    abstract fun commitsDao(): CommitsDao
    abstract fun repositoriesDao(): RepositoriesDao
}
