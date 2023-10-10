package com.filippo.repos.details.domain.model

@JvmInline
value class Sha(val value: String) {
    val shortValue: String
        get() = value.take(SHORT_SHA_COUNT)
}

private const val SHORT_SHA_COUNT = 7
