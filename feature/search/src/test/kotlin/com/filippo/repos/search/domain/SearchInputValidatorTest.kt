package com.filippo.repos.search.domain

import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import org.junit.Test

class SearchInputValidatorTest {
    private val validator
        get() = SearchInputValidator()

    @Test
    fun `should return validation error for empty input`() {
        // given
        val input = ""

        // when
        val result = validator.validate(input)

        // then
        result shouldBeLeft ValidationError.WrongFormat
    }

    @Test
    fun `should return validation error for input without dash`() {
        // given
        val input = "input"

        // when
        val result = validator.validate(input)

        // then
        result shouldBeLeft ValidationError.WrongFormat
    }

    @Test
    fun `should return validation error for input without repository name`() {
        // given
        val input = "owner/"

        // when
        val result = validator.validate(input)

        // then
        result shouldBeLeft ValidationError.EmptyName
    }

    @Test
    fun `should return validation error for input without repository owner`() {
        // given
        val input = "/name"

        // when
        val result = validator.validate(input)

        // then
        result shouldBeLeft ValidationError.EmptyOwner
    }

    @Test
    fun `should return owner and name for proper input`() {
        // given
        val input = "owner/name"

        // when
        val result = validator.validate(input)

        // then
        result shouldBeRight Pair("owner", "name")
    }
}
