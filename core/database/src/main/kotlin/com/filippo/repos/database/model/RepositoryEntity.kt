package com.filippo.repos.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.filippo.repos.database.schema.RepositoryTableConstants

@Entity(tableName = RepositoryTableConstants.TABLE_NAME)
data class RepositoryEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = RepositoryTableConstants.ID)
    val id: String,
    @ColumnInfo(name = RepositoryTableConstants.NAME)
    val name: String,
    @ColumnInfo(name = RepositoryTableConstants.OWNER)
    val owner: String,
)
