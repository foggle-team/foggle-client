package org.foggle.client.sample;

import org.foggle.client.FoggleClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleService implements CommandLineRunner {
	private final FoggleClient foggle;

	public SampleService(final FoggleClient foggle) {
		this.foggle = foggle;
	}

	@Override
	public void run(String... args) {
		System.out.println("Foggle features: " + foggle.getEnabledFeatures());
	}
}
