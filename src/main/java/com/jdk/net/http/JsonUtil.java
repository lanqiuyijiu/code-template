package com.jdk.net.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * Jackson工具类
 **/
public class JsonUtil {
    public static ObjectMapper objectMapper = new ObjectMapper();
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static ObjectNode createJson() {
        return objectMapper.createObjectNode();
    }

    /**
     * Object转json字符串
     */
    public static <T> String toJson(T obj) {
        try {
            if (obj == null) {
                return null;
            } else if (obj instanceof String) {
                return (String) obj;
            } else {
                return objectMapper.writeValueAsString(obj);
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Object转json字符串
     */
    public static JsonNode toJson(String str) {
        try {
            if (str == null) {
                return null;
            } else {
                return objectMapper.readTree(str);
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * json转object
     */
    public static <T> T toBean(String json, Class<T> clazz) {
        try {
            if (StringUtils.isEmpty(json) || clazz == null) {
                return null;
            } else {
                return objectMapper.readValue(json, clazz);
            }
        } catch (IOException e) {
            return null;
        }
    }


    /**
     * json转集合
     */
    public static <T> T toBean(String json, TypeReference<T> typeReference) {
        try {
            if (StringUtils.isEmpty(json) || typeReference == null) {
                return null;
            } else {
                return objectMapper.readValue(json, typeReference);
            }

        } catch (IOException e) {
            return null;
        }
    }


    /**
     * string转object 用于转为集合对象
     */
    public static <T> T toBean(String json, Class<?> collectionClass, Class<?>... elementClasses) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
            return objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            return null;
        }
    }
}