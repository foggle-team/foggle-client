package org.foggle.sample;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.foggle.core.dto.FeatureDto;

import java.time.LocalDateTime;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class SampleFoggleBackend {
	public static final int WIREMOCK_PORT = 8089;

	private final WireMockServer wireMockServer;
	private final ObjectMapper mapper = buildMapper();

	public SampleFoggleBackend() {
		wireMockServer = new WireMockServer(options().port(WIREMOCK_PORT));
		configureStubs();
		wireMockServer.start();
	}

	private void configureStubs() {
		wireMockServer.stubFor(get(urlEqualTo("/api/client/features/enabled")).willReturn(aResponse().withBody(mockFeatures())));
	}

	private String mockFeatures() {
		try {
			return mapper.writeValueAsString(asList(
					new FeatureDto(1, "some_feature", true, LocalDateTime.now(), LocalDateTime.now(), "some_feature", emptyList()),
					new FeatureDto(2, "another_feature", true, LocalDateTime.now(), LocalDateTime.now(), "some_feature", emptyList())
			));
		} catch (Exception e) {
			return "";
		}
	}

	public void shutdown() {
		wireMockServer.stop();
	}

	private ObjectMapper buildMapper() {
		return new ObjectMapper()
				.registerModule(new KotlinModule())
				.registerModule(new JavaTimeModule())
				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
}
