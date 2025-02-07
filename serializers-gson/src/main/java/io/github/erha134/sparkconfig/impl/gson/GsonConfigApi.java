package io.github.erha134.sparkconfig.impl.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.erha134.sparkconfig.api.ConfigApi;

import java.lang.reflect.Modifier;

public class GsonConfigApi implements ConfigApi {
    private static final Gson gson = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .excludeFieldsWithModifiers(Modifier.STATIC)
            .setPrettyPrinting()
            .create();

    @Override
    public <T> T readInternal(Class<T> clazz, String config) {
        return gson.fromJson(config, clazz);
    }

    @Override
    public <T> String write(T object) {
        return gson.toJson(object);
    }
}
