package com.filippo.repos.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.filippo.repos.database.model.RepositoryEntity
import com.filippo.repos.database.schema.RepositoryTableConstants

@Dao
interface RepositoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repository: RepositoryEntity)

    @Query("SELECT * FROM ${RepositoryTableConstants.TABLE_NAME} WHERE ${RepositoryTableConstants.OWNER} LIKE :owner AND ${RepositoryTableConstants.NAME} LIKE :name")
    suspend fun getRepository(owner: String, name: String): RepositoryEntity?
}
