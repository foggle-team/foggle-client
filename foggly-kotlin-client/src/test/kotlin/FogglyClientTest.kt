import org.foggly.client.FeatureEnum
import org.foggly.client.FogglyClient
import org.foggly.mock.SampleFogglyBackend
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@DisplayName("Foggly client")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FogglyClientTest {
    private val mockBackend = SampleFogglyBackend()
    private val client = FogglyClient("http://localhost:8089")

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

    @Test
    fun `should indicate if enabled feature enum is enabled`() {
        assertTrue(client.isEnabled(AppFeature.SOME_FEATURE))
        assertFalse(client.isDisabled(AppFeature.SOME_FEATURE))
    }

    @Test
    fun `should indicate if disabled feature enum is enabled`() {
        assertFalse(client.isEnabled(AppFeature.SOMETHING_DISABLED))
        assertTrue(client.isDisabled(AppFeature.SOMETHING_DISABLED))
    }
}

enum class AppFeature(private val path: String) : FeatureEnum {
    SOME_FEATURE("my_app:some_feature"),
    ANOTHER_FEATURE("another_feature"),
    SOMETHING_DISABLED("something_disabled");

    override fun getPath(): String {
        return path
    }
}
