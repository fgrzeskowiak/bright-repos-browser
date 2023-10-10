package com.filippo.repos.details.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryResponse(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("owner") val owner: Owner,
) {
    @Serializable
    data class Owner(@SerialName("login") val login: String)
}
