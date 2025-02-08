package io.github.erha134.sparkconfig.impl.gson;

import io.github.erha134.sparkconfig.impl.ConfigApiService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GsonConfigApiTest {
    @Test
    void spi() {
        assertTrue(ConfigApiService.getInstance().isPresent(GsonConfigApi.class));
    }
}
