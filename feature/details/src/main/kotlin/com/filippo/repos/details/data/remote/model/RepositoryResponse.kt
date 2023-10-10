package com.filippo.repos.details.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryResponse(@SerialName("id") val id: String)
