package com.json;

import com.common.pojo.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JacksonTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static ObjectNode createJson() {
        return objectMapper.createObjectNode();
    }

    /**
     * Object转json字符串
     */
    public static <T> String toJsonString(T obj) {
        try {
            if (obj == null) {
                return "";
            } else if (obj instanceof String) {
                return (String) obj;
            } else {
                return objectMapper.writeValueAsString(obj);
            }
        } catch (Exception e) {
            return "";
        }
    }

    public static <T> String toJson1(T obj) {
        try {
            if (obj == null) {
                return null;
            }
//            else if (obj instanceof String) {
//                return (String) obj;
//            }
            else {
                return objectMapper.writeValueAsString(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JsonNode toJson3(String str) {
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
            if (!StringUtils.hasText(json) || clazz == null) {
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
            if (!StringUtils.hasText(json) || typeReference == null) {
                return (T) Collections.emptyList();
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

    @Test
    public void testToJson() {
        User user = new User();
        user.setUserName("123");
        user.setPassword("456");
        user.setPhone("3333");
        user.setUserType(3);

//        System.out.println(toBean("{\"userName\":\"123\",\"password\":\"456\",\"phone\":\"3333\",\"userType\":3}", User.class));
        List<User> objects = new ArrayList<>();
        objects.add(user);
        List<User> objects1 = new ArrayList<>();
        objects1.add(user);

        List<List<User>> objects2 = new ArrayList<>();
        objects2.add(objects);
        objects2.add(objects1);

        Object o = toBean(toJson1(objects), List.class, User.class);
        System.out.println(o);
    }
}

