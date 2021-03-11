package org.foggle.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import org.foggle.core.dto.FeatureDto;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

public class FoggleClient {
	private final FoggleService service;

	public FoggleClient(final String baseUrl) {
		ObjectMapper mapper = new ObjectMapper()
				.registerModule(new KotlinModule())
				.registerModule(new JavaTimeModule())
				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(JacksonConverterFactory.create(mapper))
				.build();

		service = retrofit.create(FoggleService.class);
	}

	public List<FeatureDto> getAllowedFeatures() throws IOException {
		return service.getEnabledFeatures().execute().body();
	}
}
