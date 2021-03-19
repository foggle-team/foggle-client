package org.foggle.sample;

import org.foggle.client.FoggleClient;
import org.foggle.core.dto.FeatureDto;
import org.foggle.mock.SampleFoggleBackend;

import java.util.List;

public class SampleApp {
	private FoggleClient client;

	public SampleApp() {
		// Create Foggle client with URL of your deployed Foggle application
		client = new FoggleClient("http://localhost:8089");

		List<FeatureDto> features = client.getEnabledFeatures();

		System.out.println(features);
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
