package io.github.erha134.sparkconfig.impl.jackson.properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.google.auto.service.AutoService;
import io.github.erha134.sparkconfig.api.ConfigApi;

@AutoService(ConfigApi.class)
public class JacksonPropertiesConfigApi implements ConfigApi {
    private static final JavaPropsMapper mapper = JavaPropsMapper.builder()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .build();

    @Override
    public String getExt() {
        return "properties";
    }

    @Override
    public <T> T readInternal(Class<T> clazz, String config) {
        try {
            return mapper.readValue(config, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to deserialize toml", e);
        }
    }

    @Override
    public <T> String write(T object) {
        try {
            return mapper.writer().withDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to serialize toml", e);
        }
    }
}
