package org.foggly.core.dto

import java.time.LocalDateTime

public data class FeatureDto(
    val id: Long,
    val name: String,
    val enabled: Boolean,

    val creationDate: LocalDateTime,
    val updateDate: LocalDateTime,

    var path: String = "",
    var children: List<FeatureDto> = listOf(),
)
