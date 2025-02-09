package io.github.erha134.sparkconfig.impl.typesafe.config;

import com.google.auto.service.AutoService;
import com.typesafe.config.ConfigFactory;
import io.github.erha134.sparkconfig.api.ConfigApi;
import io.github.erha134.easylib.reflect.ReflectUtils;
import org.jetbrains.annotations.ApiStatus;

import java.util.Map;
import java.util.stream.Collectors;

@ApiStatus.Experimental
@AutoService(ConfigApi.class)
public class TypesafeConfigApi implements ConfigApi {
    @Override
    public String getExt() {
        return "cfg";
    }

    @Override
    public <T> T readInternal(Class<T> clazz, String config) {
        return ReflectUtils.convertToBean(clazz, ConfigFactory.parseString(config)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().unwrapped())));
    }

    @Override
    public <T> String write(T object) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
