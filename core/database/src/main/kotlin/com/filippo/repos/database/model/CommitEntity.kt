package com.filippo.repos.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.filippo.repos.database.schema.CommitsTableConstants

@Entity(
    tableName = CommitsTableConstants.TABLE_NAME,
    primaryKeys = [CommitsTableConstants.SHA, CommitsTableConstants.REPO_NAME]
)
data class CommitEntity(
    @ColumnInfo(name = CommitsTableConstants.REPO_NAME) val repositoryName: String,
    @ColumnInfo(name = CommitsTableConstants.SHA) val sha: String,
    @ColumnInfo(name = CommitsTableConstants.MESSAGE) val message: String,
    @ColumnInfo(name = CommitsTableConstants.AUTHOR) val author: String,
)
