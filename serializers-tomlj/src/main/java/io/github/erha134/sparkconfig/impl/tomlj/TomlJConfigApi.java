package io.github.erha134.sparkconfig.impl.tomlj;

import com.google.auto.service.AutoService;
import io.github.erha134.sparkconfig.api.ConfigApi;
import io.github.erha134.easylib.reflect.ReflectUtils;
import org.jetbrains.annotations.ApiStatus;
import org.tomlj.Toml;

@ApiStatus.Experimental
@AutoService(ConfigApi.class)
public class TomlJConfigApi implements ConfigApi {
    @Override
    public String getExt() {
        return "toml";
    }

    @Override
    public <T> T readInternal(Class<T> clazz, String config) {
        return ReflectUtils.convertToBean(clazz, Toml.parse(config).toMap());
    }

    @Override
    public <T> String write(T object) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
