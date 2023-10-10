package com.filippo.repos.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.filippo.repos.database.model.CommitEntity
import com.filippo.repos.database.schema.CommitsTableConstants

@Dao
interface CommitsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(commits: List<CommitEntity>)

    @Query("SELECT * FROM ${CommitsTableConstants.TABLE_NAME} WHERE ${CommitsTableConstants.REPO_NAME} LIKE :repositoryName")
    suspend fun getCommits(repositoryName: String): List<CommitEntity>
}
