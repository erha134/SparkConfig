package io.github.erha134.sparkconfig.impl.manager;

import io.github.erha134.sparkconfig.api.ConfigApi;
import io.github.erha134.sparkconfig.api.manager.AbstractConfigManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class FileConfigManager<T> extends AbstractConfigManager<T> {
    protected final Path path;

    public FileConfigManager(ConfigApi api, Class<T> clazz, Path path) {
        super(api, clazz);
        this.path = path;
    }

    public FileConfigManager(ConfigApi api, Class<T> clazz, File file) {
        this(api, clazz, file.toPath());
    }

    public FileConfigManager(ConfigApi api, Class<T> clazz, String file) {
        this(api, clazz, new File(file));
    }

    @Override
    public void load() throws IOException {
        this.value = this.api.read(this.clazz, this.path);
    }

    @Override
    public void save() throws IOException {
        this.api.write(this.value, this.path);
    }
}
