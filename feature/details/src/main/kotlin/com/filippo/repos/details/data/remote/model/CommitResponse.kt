package com.filippo.repos.details.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommitResponse(
    @SerialName("commit")
    val commit: Commit,
    @SerialName("sha")
    val sha: String,
) {
    @Serializable
    data class Commit(
        @SerialName("author")
        val author: Author,
        @SerialName("message")
        val message: String,
    ) {
        @Serializable
        data class Author(@SerialName("name") val name: String)
    }
}
