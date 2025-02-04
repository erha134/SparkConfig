package io.github.erha134.sparkconfig.impl;

import com.alibaba.fastjson2.JSON;
import io.github.erha134.sparkconfig.api.ConfigApi;

public class FastJson2ConfigApi implements ConfigApi {
    @Override
    public <T> T readInternal(Class<T> clazz, String config) {
        return JSON.parseObject(config, clazz);
    }

    @Override
    public <T> String write(T object) {
        return JSON.toJSONString(object);
    }
}
