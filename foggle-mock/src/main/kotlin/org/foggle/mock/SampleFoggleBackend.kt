package org.foggle.mock

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import org.foggle.core.dto.FeatureDto
import java.time.LocalDateTime

const val WIREMOCK_PORT = 8089

class SampleFoggleBackend {
    private val wireMockServer: WireMockServer = WireMockServer(WireMockConfiguration.options().port(WIREMOCK_PORT))
    private val mapper = buildMapper()

    init {
        configureStubs()
        wireMockServer.start()
    }

    private fun configureStubs() {
        wireMockServer.stubFor(
            get(urlEqualTo("/api/client/features/enabled")).willReturn(aResponse().withBody(mockFeatures()))
        )
        wireMockServer.stubFor(
            get(urlEqualTo("/api/client/features/enabled/flat")).willReturn(aResponse().withBody(mockFeatures()))
        )
    }

    private fun mockFeatures(): String {
        return mapper.writeValueAsString(listOf(
            FeatureDto(1, "some_feature", true, LocalDateTime.now(), LocalDateTime.now(), "my_app:some_feature", emptyList()),
            FeatureDto(2, "another_feature", true, LocalDateTime.now(), LocalDateTime.now(), "another_feature", emptyList())
        ))
    }

    fun shutdown() {
        wireMockServer.stop()
    }

    private fun buildMapper(): ObjectMapper {
        return ObjectMapper()
            .registerModule(KotlinModule())
            .registerModule(JavaTimeModule())
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    }
}
