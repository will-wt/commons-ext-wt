package com.will.commons.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;

import java.lang.reflect.Type;

/**
 * @author Will.WT
 * @date 2022/10/28 23:41
 */
public class GsonHelper {

    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            // 反序列化时object转number策略，int/long转成long
            .setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
            .create();



    public static String toJson(Object src){
        return getInstance().toJson(src);
    }

    public static String toJson(Object src, Type type){
        return getInstance().toJson(src, type);
    }

    public static <T> T fromJson(String json, Class<T> classz){
        return getInstance().fromJson(json, classz);
    }

    public static <T> T fromJson(String json, Type type){
        return getInstance().fromJson(json, type);
    }

    public static Gson getInstance(){
        return gson;
    }

}
