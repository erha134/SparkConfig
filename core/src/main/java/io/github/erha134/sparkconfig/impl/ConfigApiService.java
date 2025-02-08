package io.github.erha134.sparkconfig.impl;

import io.github.erha134.easylib.stream.StreamUtils;
import io.github.erha134.easylib.util.IterateUtils;
import io.github.erha134.sparkconfig.api.ConfigApi;

import java.util.Map;
import java.util.ServiceLoader;
import java.util.function.Function;

public final class ConfigApiService {
    private static ConfigApiService INSTANCE;

    public static ConfigApiService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConfigApiService();
        }

        return INSTANCE;
    }

    private final Map<Class<? extends ConfigApi>, ConfigApi> map;

    private ConfigApiService() {
        ServiceLoader<ConfigApi> loader = ServiceLoader.load(ConfigApi.class);
        this.map = StreamUtils.toMap(IterateUtils.toStream(loader.iterator()), ConfigApi::getClass, Function.identity());
    }

    public <T extends ConfigApi> boolean isPresent(Class<T> cls) {
        return this.map.containsKey(cls);
    }

    public <T extends ConfigApi> T get(Class<T> cls) {
        return (T) this.map.get(cls);
    }
}
