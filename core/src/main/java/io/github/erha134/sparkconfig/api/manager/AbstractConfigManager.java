package io.github.erha134.sparkconfig.api.manager;

import io.github.erha134.sparkconfig.api.ConfigApi;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractConfigManager<T> implements ConfigManager<T> {
    protected final ConfigApi api;
    protected final Class<T> clazz;

    protected T value;

    @Override
    public T get() {
        return this.value;
    }

    @Override
    public void set(T t) {
        this.value = t;
    }
}
