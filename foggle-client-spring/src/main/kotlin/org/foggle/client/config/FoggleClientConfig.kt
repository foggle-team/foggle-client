package org.foggle.client.config

import org.foggle.client.FoggleClient
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(FoggleConfig::class)
open class FoggleClientConfig {
    @Bean
    open fun foggleClient(config: FoggleConfig): FoggleClient {
        return FoggleClient(config.baseUrl)
    }
}


@ConstructorBinding
@ConfigurationProperties(prefix = "foggle")
data class FoggleConfig(val baseUrl: String)
