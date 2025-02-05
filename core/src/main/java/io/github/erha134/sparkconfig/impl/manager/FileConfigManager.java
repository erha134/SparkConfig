package io.github.erha134.sparkconfig.impl.manager;

import io.github.erha134.sparkconfig.api.ConfigApi;
import io.github.erha134.sparkconfig.api.manager.AbstractConfigManager;

import java.io.File;
import java.io.IOException;

public class FileConfigManager<T> extends AbstractConfigManager<T> {
    protected final File file;

    public FileConfigManager(ConfigApi api, Class<T> clazz, File file) {
        super(api, clazz);
        this.file = file;
    }

    public FileConfigManager(ConfigApi api, Class<T> clazz, String file) {
        this(api, clazz, new File(file));
    }

    @Override
    public void load() throws IOException {
        this.value = this.api.read(this.clazz, this.file);
    }

    @Override
    public void save() throws IOException {
        this.api.write(this.value, this.file);
    }
}
