package io.github.erha134.sparkconfig.impl.lang;

import com.google.auto.service.AutoService;
import io.github.erha134.sparkconfig.api.ConfigApi;
import io.github.erha134.easylib.reflect.ReflectUtils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

@AutoService(ConfigApi.class)
public class JDKPropertiesConfigApi implements ConfigApi {
    @Override
    public String getExt() {
        return "properties";
    }

    @Override
    public <T> T readInternal(Class<T> clazz, String config) {
        try {
            Properties properties = new Properties();
            properties.load(new StringReader(config));

            return ReflectUtils.convertToBean(clazz, properties.entrySet()
                    .stream()
                    .collect(Collectors.toMap(e -> (String) e.getKey(), Map.Entry::getValue)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize properties", e);
        }
    }

    @Override
    public <T> String write(T object) {
        Properties properties = new Properties();
        properties.putAll(ReflectUtils.convertToMap(object));

        try (StringWriter sw = new StringWriter()) {
            properties.store(sw, null);
            sw.flush();
            return sw.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to serialize properties", e);
        }
    }
}
