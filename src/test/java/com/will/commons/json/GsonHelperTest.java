package com.will.commons.json;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Will.WT
 * @date 2022/10/30 20:36
 */
public class GsonHelperTest {

    private Map<String, Object> testMap;
    private Person person;
    private Gson gson;
    private String dateStr = "2022-10-29 12:20:30";

    @Before
    public void init() throws ParseException {
        Date date = DateUtils.parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");

        testMap = new HashMap<String, Object>();
        testMap.put("int", 100);
        testMap.put("long", 91234567890L);
        testMap.put("double", 99.9);
        testMap.put("str", "hello");
        testMap.put("date", date);

        Map<String, Object> subMap = new HashMap<String, Object>();
        subMap.put("int", 100);
        subMap.put("long", 91234567890L);
        subMap.put("double", 99.9);
        subMap.put("str", "hello");
        subMap.put("date", date);
        testMap.put("subMap", subMap);



        Map<String, Object> scoreMap = new HashMap<String, Object>();
        scoreMap.put("yuwen", 90);
        scoreMap.put("shuxue", 100);
        scoreMap.put("yingyu", 85);

        person = Person.builder()
                .gmtCreate(date)
                .userId(100001L)
                .name("ZhangSan")
                .age(40)
                .addressList(Arrays.asList(new String[]{"hangzhou", "shanghai", "beijing"}))
                .scoreMap(scoreMap)
                .children(Lists.newArrayList(Person.builder()
                                .gmtCreate(date)
                                .userId(100002L)
                                .name("ZhangSi")
                                .age(16)
                                .addressList(Arrays.asList(new String[]{"hangzhou", "shanghai", "beijing"}))
                                .scoreMap(scoreMap)
                                .build())
                )
              .build();

        gson = new GsonBuilder().create();
    }

    @Test
    public void testJsonByMap(){
        String json1 = gson.toJson(testMap);
        System.out.println("json1="+ json1);
        assertFalse(json1.indexOf("100.0") > 0);


        Map<String, Object> map = gson.fromJson(json1, new TypeToken<Map<String, Object>>(){}.getType());
        assertNotNull(map);

        System.out.println("json2="+ gson.toJson(map));

        assertEquals( 100.0, map.get("int"));
        assertEquals(100.0, ((Map)map.get("subMap")).get("int"));
    }

    @Test
    public void testJsonByBean(){
        String json1 = gson.toJson(person);
        System.out.println("json1="+ json1);
        assertFalse(json1.indexOf("85.0") > 0);

        Person person = gson.fromJson(json1, Person.class);
        assertNotNull(person);
        assertNotNull(person.getUserId());
        System.out.println("json2="+ gson.toJson(person));
    }

    @Test
    public void testGsonHelper(){
        String json1 = GsonHelper.toJson(testMap);
        System.out.println("json1="+ json1);
        assertFalse(json1.indexOf("100.0") > 0);

        String json2 = GsonHelper.toJson(person);
        System.out.println("json2="+ json2);
        assertFalse(json1.indexOf("85.0") > 0);

        Map<String, Object> map = GsonHelper.fromJson(json1, new TypeToken<Map<String, Object>>() {}.getType());
        assertNotNull(map);
        System.out.println("json1-1="+ GsonHelper.toJson(map));
        assertEquals( 100L, map.get("int"));
        assertEquals(100L, ((Map)map.get("subMap")).get("int"));

        Person personTemp = GsonHelper.fromJson(json2, Person.class);
        assertNotNull(personTemp);
        assertNotNull(personTemp.getUserId());
        System.out.println("json2-2="+ GsonHelper.toJson(personTemp));
    }

    @Test
    public void testListMap(){
        List<Map<String, Object>> testList = new ArrayList<Map<String, Object>>();
        testList.add(testMap);

        String json1 = GsonHelper.toJson(testList);
        assertNotNull(json1);
        System.out.println("json1="+ json1);

        List<Map<String, Object>> listTemp = GsonHelper.fromJson(json1, new TypeToken<List<Map<String, Object>>>() {}.getType());
        assertNotNull(listTemp);
        System.out.println("json2="+ GsonHelper.toJson(listTemp));

        assertEquals(100L, listTemp.get(0).get("int"));
        assertEquals(100L, ((Map)listTemp.get(0).get("subMap")).get("int"));
    }


}
