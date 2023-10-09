package com.filippo.repos

import groovy.lang.MissingPropertyException
import java.io.File
import java.io.FileNotFoundException
import org.gradle.api.Project

object SecretsUtil {
    fun getSecretFromFile(secretKey: String, rootProject: Project? = null): String {
        val secretsFile = rootProject?.file(SECRETS_FILE_NAME) ?: File(SECRETS_FILE_NAME)
        if (!secretsFile.exists()) {
            throw FileNotFoundException(
                "File ${secretsFile.path} not found! It should be placed in the project root folder"
            )
        }
        val secrets = secretsFile.readLines()
            .associate { line ->
                val (key, value) = line.split(KEY_SECRET_SPLIT_CHARACTER)
                key to value
            }
        val secretToReturn = secrets[secretKey]
        if (secretToReturn.isNullOrEmpty()) {
            throw MissingPropertyException(
                "Missing property: $secretKey. Please, check configuration inside $SECRETS_FILE_NAME file."
            )
        }
        return secretToReturn
    }

    fun getStringSecret(key: String, rootProject: Project? = null): String =
        "\"${getSecretFromFile(key, rootProject)}\""
}

private const val SECRETS_FILE_NAME = "secret.properties"
private const val KEY_SECRET_SPLIT_CHARACTER = "="
