package com.wt.commons.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Gson工具类
 * @author Will.WT
 * @date 2022/10/28 23:41
 */
public class GsonHelper {

    private static Gson gson = null;

    /* 空类，用于加锁 */
    private static class Lock { };
    private static Object lock = new Lock();

    public static Gson getInstance(){
        if (gson != null){
            return gson;
        }

        // 加锁，防并发
        synchronized (lock){
            if (gson != null){
                return gson;
            }

            gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    // 反序列化时object转number策略，int/long转成long
                    .setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
                    .create();
        }

        return gson;
    }


    /**
     * 序列化
     * @param src
     * @return
     */
    public static String toJson(Object src){
        return getInstance().toJson(src);
    }

    public static String toJson(Object src, Type type){
        return getInstance().toJson(src, type);
    }


    /**
     * 反序列化
     * @param json
     * @param classz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> classz){
        return getInstance().fromJson(json, classz);
    }

    public static Map<String, Object> fromJsonMap(String json){
        return getInstance().fromJson(json,
                                new TypeToken<Map<String, Object>>() {}.getType());
    }

    public static List<Map<String, Object>> fromJsonList(String json){
        return getInstance().fromJson(json,
                                new TypeToken<List<Map<String, Object>>>() {}.getType());
    }

    public static <T> T fromJson(String json, Type type){
        return getInstance().fromJson(json, type);
    }

}
