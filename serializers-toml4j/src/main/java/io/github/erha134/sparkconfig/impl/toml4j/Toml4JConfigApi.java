package io.github.erha134.sparkconfig.impl.toml4j;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import io.github.erha134.sparkconfig.api.ConfigApi;

public class Toml4JConfigApi implements ConfigApi {
    @Override
    public <T> T readInternal(Class<T> clazz, String config) {
        return new Toml().read(config).to(clazz);
    }

    @Override
    public <T> String write(T object) {
        return new TomlWriter().write(object);
    }
}
