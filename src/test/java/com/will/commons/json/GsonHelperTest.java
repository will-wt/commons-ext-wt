package com.will.commons.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Will.WT
 * @date 2022/10/30 20:36
 */
public class GsonHelperTest {

    private Map<String, Object> testMap;
    private Person person;
    private Gson gson;

    @Before
    public void init(){
        testMap = new HashMap<String, Object>();
        testMap.put("int", 100);
        testMap.put("long", 91234567890L);
        testMap.put("double", 99.9);
        testMap.put("str", "hello");
        testMap.put("date", new Date());

        Map<String, Object> subMap = new HashMap<String, Object>();
        subMap.put("int", 100);
        subMap.put("long", 91234567890L);
        subMap.put("double", 99.9);
        subMap.put("str", "hello");
        subMap.put("date", new Date());
        testMap.put("subMap", subMap);

        Person.builder()
                .gmtCreate(new Date())
                .userId(100001L)
                .name("ZhangSan")
                .age(40)
                .addressList(Arrays.asList(new String[]{"hangzhou", "shanghai", "beijing"}))
//                .scoreMap(new HashMap<String, Object>(){"yuwen":80})
              .build();


        gson = new GsonBuilder().create();
    }

    @Test
    public void testToJson(){
        String json = gson.toJson(testMap);
        System.out.println("toJson="+ json);


        Map<String, Object> map = GsonHelper.fromJson(json, new TypeToken<Map<String, Object>>(){}.getType());

        System.out.println(map.get("int"));
    }



}
