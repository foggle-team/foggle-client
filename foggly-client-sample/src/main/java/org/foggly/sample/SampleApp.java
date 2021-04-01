package org.foggly.sample;

import org.foggly.client.FeatureEnum;
import org.foggly.client.FogglyClient;
import org.foggly.core.dto.FeatureDto;
import org.foggly.mock.SampleFogglyBackend;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SampleApp {
	private FogglyClient foggly;

	public SampleApp() {
		// Create Foggly client with URL of your deployed Foggly application
		foggly = new FogglyClient("http://localhost:8089");

		List<FeatureDto> features = foggly.getEnabledFeatures();
		System.out.println(features);

		if (foggly.isEnabled(AppFeature.SOME_FEATURE)) {
			System.out.println("I am enabled.");
		}

		if (foggly.isDisabled("another_feature")) {
			System.out.println("This is disabled");
		}
	}

	public static void main(String[] args) {
		// Start a mock Foggly backend to answer our requests
		SampleFogglyBackend mockBackend = new SampleFogglyBackend();

		// Here is your application here
		new SampleApp();

		// Shutdown mock Foggly backend
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
