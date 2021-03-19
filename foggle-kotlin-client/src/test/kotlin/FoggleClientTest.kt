import org.foggle.client.FoggleClient
import org.foggle.mock.SampleFoggleBackend
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@DisplayName("Foggle Client")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FoggleClientTest {
    private val mockBackend = SampleFoggleBackend()
    private val client = FoggleClient("http://localhost:8089")

    @AfterAll
    fun shutdown() {
        mockBackend.shutdown()
    }

    @Test
    fun `should return list of enabled features`() {
        val features = client.enabledFeatures

        assertNotNull(features)
        assertTrue(features.isNotEmpty())
    }

    @Test
    fun `should indicate if enabled feature is enabled`() {
        assertTrue(client.isEnabled("my_app:some_feature"))
        assertFalse(client.isDisabled("my_app:some_feature"))
    }

    @Test
    fun `should indicate if disabled feature is enabled`() {
        assertFalse(client.isEnabled("something_disabled"))
        assertTrue(client.isDisabled("something_disabled"))
    }

    @Test
    fun `should return list of matching enabled features`() {
        val features = client.getEnabledFeatures("my_app")
        assertEquals(1, features.size)
        assertEquals("some_feature", features[0].name)
    }
}
