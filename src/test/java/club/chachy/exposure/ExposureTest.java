package club.chachy.exposure;

import club.chachy.exposure.data.ExposureData;
import org.junit.jupiter.api.*;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExposureTest {
    private ExposureData exposureData;

    @Test
    @DisplayName("Parse mock data into a kotlin data-class (via GSON)")
    @Order(0)
    void testDataParsing() {
        InputStream stream = this.getClass().getResourceAsStream("/exposure_data.json");
        assertNotNull(stream);
        exposureData = AppleExposureData.INSTANCE.parse(stream);
    }

    @Test
    @DisplayName("Test data has been correctly parsed")
    @Order(1)
    void testData() {
        assertNotNull(exposureData);

        assertEquals("build_id", exposureData.getExposureBuild());
        assertEquals(2, exposureData.getExportVersion());
        assertEquals("JUnitRunner", exposureData.getDeviceType());

        List<ExposureData.ExposureCheck> checks = exposureData.getExposureChecks();
        assertEquals(1, checks.size());

        ExposureData.ExposureCheck exposureCheck = checks.get(0);

        assertEquals("2021-09-13 16:41:57 +0100", exposureCheck.getTimestamp());
        assertEquals("club.chachy.exposure", exposureCheck.getAppIdentifier());

        List<ExposureData.ExposureCheck.ExposureFile> exposureFiles = exposureCheck.getFiles();
        assertEquals(1, exposureFiles.size());

        ExposureData.ExposureCheck.ExposureFile exposureFile = exposureFiles.get(0);
        assertEquals("fake_hash_data", exposureFile.getHash());
        assertEquals(120, exposureFile.getKeyCount());
        assertEquals("build_id", exposureData.getExposureBuild());
    }
}
