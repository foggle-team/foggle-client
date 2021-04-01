import org.foggly.client.FogglyClient;
import org.foggly.core.dto.FeatureDto;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class ClientTest {
	@Test
	public void getEnabledFeatures() throws IOException {
		FogglyClient client = new FogglyClient("http://localhost:42777");
		List<FeatureDto> features = client.getEnabledFeatures();
		System.out.println(features);
	}
}
