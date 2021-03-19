import org.foggle.client.FoggleClient
import org.junit.jupiter.api.Test

class ClientTest {
    @Test
    fun enabledFeatures() {
        val client = FoggleClient("http://localhost:42777")
        val features = client.allowedFeatures
        println(features)

        println(client.isEnabled("payments:modify-payment:amazing-feature"))
        println(client.isEnabled("something-disabled"))
    }
}
