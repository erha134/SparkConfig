package io.github.erha134.sparkconfig.api.manager;

import java.io.IOException;
import java.util.function.Supplier;

public interface ConfigManager<T> extends Supplier<T> {
    void load() throws IOException;

    T get();

    default boolean isPresent() {
        return this.get() != null;
    }

    void set(T t);

    void save() throws IOException;

    default void reload() throws IOException {
        this.save();
        this.load();
    }
}
