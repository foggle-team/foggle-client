package org.foggle.sample;

import org.foggle.client.FeatureEnum;
import org.foggle.client.FoggleClient;
import org.foggle.core.dto.FeatureDto;
import org.foggle.mock.SampleFoggleBackend;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SampleApp {
	private FoggleClient foggle;

	public SampleApp() {
		// Create Foggle client with URL of your deployed Foggle application
		foggle = new FoggleClient("http://localhost:8089");

		List<FeatureDto> features = foggle.getEnabledFeatures();
		System.out.println(features);

		if (foggle.isEnabled(AppFeature.SOME_FEATURE)) {
			System.out.println("I am enabled.");
		}

		if (foggle.isDisabled("another_feature")) {
			System.out.println("This is disabled");
		}
	}

	public static void main(String[] args) {
		// Start a mock Foggle backend to answer our requests
		SampleFoggleBackend mockBackend = new SampleFoggleBackend();

		// Here is your application here
		new SampleApp();

		// Shutdown mock Foggle backend
		mockBackend.shutdown();
	}
}

enum AppFeature implements FeatureEnum {
	SOME_FEATURE("my_app:some_feature"),
	ANOTHER_FEATURE("some_feature");

	private final String path;

	AppFeature(String path) {
		this.path = path;
	}

	@NotNull
	@Override
	public String getPath() {
		return path;
	}
}
