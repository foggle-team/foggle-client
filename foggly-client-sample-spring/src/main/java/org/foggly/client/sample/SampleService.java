package org.foggly.client.sample;

import org.foggly.client.FogglyClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleService implements CommandLineRunner {
	private final FogglyClient foggly;

	public SampleService(final FogglyClient foggly) {
		this.foggly = foggly;
	}

	@Override
	public void run(String... args) {
		System.out.println("Foggly features: " + foggly.getEnabledFeatures());
	}
}
