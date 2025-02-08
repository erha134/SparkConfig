package io.github.erha134.sparkconfig.impl.fastjson;

import com.alibaba.fastjson2.JSON;
import com.google.auto.service.AutoService;
import io.github.erha134.sparkconfig.api.ConfigApi;

@AutoService(ConfigApi.class)
public class FastJsonConfigApi implements ConfigApi {
    @Override
    public <T> T readInternal(Class<T> clazz, String config) {
        return JSON.parseObject(config, clazz);
    }

    @Override
    public <T> String write(T object) {
        return JSON.toJSONString(object);
    }
}
