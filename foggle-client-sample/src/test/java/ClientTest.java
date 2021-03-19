import org.foggle.client.FoggleClient;
import org.foggle.core.dto.FeatureDto;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class ClientTest {
	@Test
	public void getEnabledFeatures() throws IOException {
		FoggleClient client = new FoggleClient("http://localhost:42777");
		List<FeatureDto> features = client.getEnabledFeatures();
		System.out.println(features);
	}
}
