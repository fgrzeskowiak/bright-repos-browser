package com.filippo.repos.details.domain.model

data class Commit(
    val author: String,
    val sha: Sha,
    val message: String,
)
