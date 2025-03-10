package io.github.erha134.sparkconfig.impl.jankson;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.api.SyntaxError;
import com.google.auto.service.AutoService;
import io.github.erha134.sparkconfig.api.ConfigApi;

@AutoService(ConfigApi.class)
public class JanksonConfigApi implements ConfigApi {
    private static final Jankson jankson = Jankson.builder()
            .build();

    @Override
    public String getExt() {
        return "json5";
    }

    @Override
    public <T> T readInternal(Class<T> clazz, String config) {
        try {
            return jankson.fromJson(config, clazz);
        } catch (SyntaxError e) {
            throw new IllegalArgumentException("Failed to deserialize json", e);
        }
    }

    @Override
    public <T> String write(T object) {
        return jankson.toJson(object).toJson(true, true);
    }
}
