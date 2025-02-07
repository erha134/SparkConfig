package io.github.erha134.sparkconfig.impl.jackson.toml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.toml.TomlMapper;
import io.github.erha134.sparkconfig.api.ConfigApi;

public class JacksonTomlConfigApi implements ConfigApi {
    private static final TomlMapper mapper = TomlMapper.builder()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .build();

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
