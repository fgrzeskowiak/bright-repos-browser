package com.filippo.repos.database.schema

internal const val DATABASE_NAME = "github_database"

internal object RepositoryTableConstants {
    internal const val TABLE_NAME = "repositories"
    internal const val ID = "repo_id"
    internal const val NAME = "repo_name"
    internal const val OWNER = "repo_owner"
}

internal object CommitsTableConstants {
    internal const val TABLE_NAME = "commits"
    internal const val REPO_NAME = "repo_name"
    internal const val SHA = "sha"
    internal const val AUTHOR = "author"
    internal const val MESSAGE = "message"
}
