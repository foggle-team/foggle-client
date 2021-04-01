package org.foggly.client.config

import org.foggly.client.FogglyClient
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(FogglyConfig::class)
open class FogglyClientConfig {
    @Bean
    open fun fogglyClient(config: FogglyConfig): FogglyClient {
        return FogglyClient(config.baseUrl)
    }
}


@ConstructorBinding
@ConfigurationProperties(prefix = "foggly")
data class FogglyConfig(val baseUrl: String)
