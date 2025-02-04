package io.github.erha134.sparkconfig.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.github.erha134.sparkconfig.api.ConfigApi;

public class JacksonJsonConfigApi implements ConfigApi {
    private static final JsonMapper mapper = JsonMapper.builder()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .build();

    @Override
    public <T> T readInternal(Class<T> clazz, String config) {
        try {
            return mapper.readValue(config, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to deserialize json", e);
        }
    }

    @Override
    public <T> String write(T object) {
        try {
            return mapper.writer().withDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to serialize json", e);
        }
    }
}
