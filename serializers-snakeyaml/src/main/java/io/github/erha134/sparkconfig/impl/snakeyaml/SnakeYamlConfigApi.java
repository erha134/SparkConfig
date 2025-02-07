package io.github.erha134.sparkconfig.impl.snakeyaml;

import io.github.erha134.sparkconfig.api.ConfigApi;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;

public class SnakeYamlConfigApi implements ConfigApi {
    private final Yaml yaml = new Yaml();

    @Override
    public <T> T readInternal(Class<T> clazz, String config) {
        return yaml.load(config);
    }

    @Override
    public <T> String write(T object) {
        return yaml.dumpAs(object, Tag.MAP, null);
    }
}
