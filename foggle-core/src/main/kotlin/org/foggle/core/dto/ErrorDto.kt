package org.foggle.core.dto

import java.util.*

data class ErrorDto(
    val code: String,
    val message: String?,
    val uuid: String = UUID.randomUUID().toString()
)
