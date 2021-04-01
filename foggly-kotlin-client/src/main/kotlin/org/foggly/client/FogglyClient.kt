package org.foggly.client

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.foggly.client.exception.FogglyException
import org.foggly.core.dto.FeatureDto
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

/**
 * Foggly client to interact with backend (check if feature is enabled, ...)
 *
 * @param baseUrl Base URL of Foggly backend (ie. "http://<foggly_host>:<foggly_port>")
 */
class FogglyClient(baseUrl: String) {
    private val service: FogglyService

    /**
     * Returns the list of all enabled features
     * @return List of enabled features
     */
    val enabledFeatures: List<FeatureDto>
        get() {
            try {
                return service.enabledFeatures().execute().body()!!
            } catch (e: Exception) {
                throw FogglyException("Failed to retrieve list of enabled features", e)
            }
        }

    init {
        val mapper = ObjectMapper()
            .registerModule(KotlinModule())
            .registerModule(JavaTimeModule())
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(JacksonConverterFactory.create(mapper))
            .build()

        service = retrofit.create(FogglyService::class.java)
    }

    /**
     * Indicates if given feature is enabled
     *
     * @param featurePath Feature full path (ie. "my-app:my-feature")
     * @return True if feature is enabled
     */
    fun isEnabled(featurePath: String): Boolean {
        return enabledFeatures.find { f -> f.path == featurePath } != null
    }

    /**
     * Indicates if given feature is disabled
     *
     * @param featurePath Feature full path (ie. "my-app:my-feature")
     * @return True if feature is disabled
     */
    fun isDisabled(featurePath: String): Boolean {
        return !isEnabled(featurePath)
    }

    /**
     * Indicates if given feature is enabled
     *
     * @param feature Feature enum item containing full path
     * @return True if feature is enabled
     */
    fun isEnabled(feature: FeatureEnum): Boolean {
        return isEnabled(feature.getPath())
    }

    /**
     * Indicates if given feature is disabled
     *
     * @param feature Feature enum item containing full path
     * @return True if feature is enabled
     */
    fun isDisabled(feature: FeatureEnum): Boolean {
        return isDisabled(feature.getPath())
    }

    /**
     * Returns the list of enabled features, starting with the given path
     *
     * @return List of enabled features
     */
    fun getEnabledFeatures(prefixPath: String): List<FeatureDto> {
        return enabledFeatures.filter { it.path.startsWith(prefixPath, true) }
    }
}
