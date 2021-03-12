package org.foggle.client

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.foggle.core.dto.FeatureDto
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.io.IOException

class FoggleClient(baseUrl: String) {
    private val service: FoggleService

    @get:Throws(IOException::class)
    val allowedFeatures: List<FeatureDto>
        get() = service.enabledFeatures().execute().body()!!

    init {
        val mapper = ObjectMapper()
            .registerModule(KotlinModule())
            .registerModule(JavaTimeModule())
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(JacksonConverterFactory.create(mapper))
            .build()

        service = retrofit.create(FoggleService::class.java)
    }
}
