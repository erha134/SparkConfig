package io.github.erha134.sparkconfig.impl.manager;

import io.github.erha134.sparkconfig.api.ConfigApi;
import io.github.erha134.sparkconfig.api.manager.AbstractConfigManager;

import java.io.IOException;

public class SimpleConfigManager<T> extends AbstractConfigManager<T> {
    private String config;

    public SimpleConfigManager(ConfigApi api, Class<T> clazz, String config) {
        super(api, clazz);
        this.config = config;
    }

    @Override
    public void load() throws IOException {
        this.value = this.api.read(this.clazz, this.config);
    }

    @Override
    public void save() throws IOException {
        this.config = this.api.write(this.value);
    }
}
