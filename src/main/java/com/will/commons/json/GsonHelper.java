package com.will.commons.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Will.WT
 * @date 2022/10/28 23:41
 */
public class GsonHelper {

    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(new TypeToken<Map<String, Object>>(){}.getType(), new MapTypeAdapter())
            .create();


    public static String toJson(Object o){
        return getInstance().toJson(o);
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



    /**
     * Map反序列化
     */
    public static class MapTypeAdapter extends TypeAdapter<Object> {

        @Override
        public Object read(JsonReader in) throws IOException {
            JsonToken token = in.peek();
            switch (token) {
                case BEGIN_ARRAY:
                    List<Object> list = new ArrayList<Object>();
                    in.beginArray();
                    while (in.hasNext()) {
                        list.add(read(in));
                    }
                    in.endArray();
                    return list;

                case BEGIN_OBJECT:
                    Map<String, Object> map = new LinkedTreeMap<String, Object>();
                    in.beginObject();
                    while (in.hasNext()) {
                        map.put(in.nextName(), read(in));
                    }
                    in.endObject();
                    return map;

                case STRING:
                    return in.nextString();

                case NUMBER:
                    // 改写数字的处理逻辑，将数字值分为整型与浮点型。
                    double dbNum = in.nextDouble();

                    // 数字超过long的最大值，返回浮点类型
                    if (dbNum > Long.MAX_VALUE) {
                        return dbNum;
                    }

                    // 判断数字是否为整数值
                    long lngNum = (long) dbNum;
                    if (dbNum == lngNum) {
                        return lngNum;
                    } else {
                        return dbNum;
                    }

                case BOOLEAN:
                    return in.nextBoolean();

                case NULL:
                    in.nextNull();
                    return null;

                default:
                    throw new IllegalStateException();
            }
        }

        @Override
        public void write(JsonWriter out, Object value) throws IOException {
            // 序列化无需实现
        }
    }

}
