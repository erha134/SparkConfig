package io.github.erha134.sparkconfig.impl;

import io.github.erha134.sparkconfig.api.ConfigApi;
import io.github.erha134.sparkconfig.api.util.ReflectUtils;
import org.tomlj.Toml;

public class TomlJConfigApi implements ConfigApi {
    @Override
    public <T> T readInternal(Class<T> clazz, String config) {
        return ReflectUtils.convertToBean(clazz, Toml.parse(config).toMap());
    }

    @Override
    public <T> String write(T object) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
