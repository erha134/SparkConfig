package io.github.erha134.sparkconfig.api;

import io.github.erha134.sparkconfig.api.util.ReflectUtils;
import org.jetbrains.annotations.ApiStatus;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * SparkConfig 的基本 API。
 * 提供了读取和写入配置文件的方法。
 * <br>
 * 开发者指南 - 如何适配更多的解析器/（反）序列化器：
 * <ol>
 *     <li>实现 {@link ConfigApi} 接口</li>
 *     <li>实现 {@link #readInternal(Class, String)} 方法</li>
 *     <li>实现 {@link #write(Object)} 方法</li>
 * </ol>
 * @since 1.0.0
 */
public interface ConfigApi {
    // deserialization methods

    @ApiStatus.Internal
    <T> T readInternal(Class<T> clazz, String config);

    /**
     * 读取配置内容，并创建对应类的新对象。
     * @param clazz 对应类
     * @param config 配置内容（就是文件的内容）
     * @return 新对象
     * @param <T> 对应类的类型
     */
    default <T> T read(Class<T> clazz, String config) {
        T instance = this.readInternal(clazz, config);

        if (instance == null) {
            return ReflectUtils.newInstance(clazz);
        }

        return instance;
    }

    /**
     * 读取配置文件，并创建对应类的新对象。
     * @param clazz 对应类
     * @param path 配置文件路径
     * @return 新对象
     * @param <T> 对应类的类型
     * @throws IOException 文件读取失败
     */
    default <T> T read(Class<T> clazz, Path path) throws IOException {
        return this.read(clazz, Files.newInputStream(path));
    }

    /**
     * 读取配置文件，并创建对应类的新对象。
     * @param clazz 对应类
     * @param file 配置文件
     * @return 新对象
     * @param <T> 对应类的类型
     * @throws IOException 文件读取失败
     */
    default <T> T read(Class<T> clazz, File file) throws IOException {
        return this.read(clazz, file.toPath());
    }

    /**
     * 读取 {@link InputStream} 对象中的配置内容，并创建对应类的新对象。
     * @param clazz 对应类
     * @param is {@link InputStream} 对象
     * @return 新对象
     * @param <T> 对应类的类型
     * @throws IOException {@link InputStream} 读取失败
     */
    default <T> T read(Class<T> clazz, InputStream is) throws IOException {
        return this.read(clazz, new InputStreamReader(is, StandardCharsets.UTF_8));
    }

    /**
     * 读取 {@link Reader} 对象中的配置内容，并创建对应类的新对象。
     * @param clazz 对应类
     * @param reader {@link Reader} 对象
     * @return 新对象
     * @param <T> 对应类的类型
     * @throws IOException {@link Reader} 读取失败
     */
    default <T> T read(Class<T> clazz, Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(reader)) {
            String line = br.readLine();

            while (line != null) {
                sb.append(line).append('\n');
                line = br.readLine();
            }
        }

        return this.read(clazz, sb.toString());
    }

    /**
     * 读取字节数组中的配置内容，并创建对应类的新对象。
     * @param clazz 对应类
     * @param bytes 字节数组
     * @return 新对象
     * @param <T> 对应类的类型
     */
    default <T> T readFromBytes(Class<T> clazz, byte[] bytes) {
        return this.read(clazz, new String(bytes, StandardCharsets.UTF_8));
    }

    // serialization methods

    /**
     * 将对象转换为配置内容。
     * @param object 对象
     * @return 配置内容
     * @param <T> 对象的类型
     */
    <T> String write(T object);

    /**
     * 将对象转换为配置内容，并写入配置文件。
     * @param object 对象
     * @param path 配置文件路径
     * @param <T> 对象类型
     * @throws IOException 文件写入失败
     */
    default <T> void write(T object, Path path) throws IOException {
        this.write(object, Files.newOutputStream(path));
    }

    /**
     * 将对象转换为配置内容，并写入配置文件。
     * @param object 对象
     * @param file 文件
     * @param <T> 对象类型
     * @throws IOException 文件写入失败
     */
    default <T> void write(T object, File file) throws IOException {
        this.write(object, file.toPath());
    }

    /**
     * 将对象转换为配置内容，并写入 {@link OutputStream} 对象。
     * @param object 对象
     * @param os {@link OutputStream} 对象
     * @param <T> 对象类型
     * @throws IOException {@link OutputStream} 写入失败
     */
    default <T> void write(T object, OutputStream os) throws IOException {
        this.write(object, new OutputStreamWriter(os, StandardCharsets.UTF_8));
    }

    /**
     * 将对象转换为配置内容，并写入 {@link Writer} 对象。
     * @param object 对象
     * @param writer {@link Writer} 对象
     * @param <T> 对象类型
     * @throws IOException {@link Writer} 写入失败
     */
    default <T> void write(T object, Writer writer) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(this.write(object));
            bw.flush();
        }
    }

    /**
     * 将对象转换为配置内容，并返回字节数组。
     * @param object 对象
     * @return 字节数组
     * @param <T> 对象类型
     */
    default <T> byte[] writeToBytes(T object) {
        return this.write(object).getBytes(StandardCharsets.UTF_8);
    }
}
