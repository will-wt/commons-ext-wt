package com.wt.commons.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Will.WT
 * @date 2022/10/30 20:36
 */
public class GsonHelperTest extends BaseJsonTest {

    protected Gson gson;

    @Before
    public void init() throws ParseException {
        super.initData();

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

        Map<String, Object> map = GsonHelper.fromJson(json1, new TypeToken<Map<String, Object>>() {}.getType());
        assertNotNull(map);
        System.out.println("json1-1="+ GsonHelper.toJson(map));
        assertEquals( 100L, map.get("int"));
        assertEquals(100L, ((Map)map.get("subMap")).get("int"));



        String json2 = GsonHelper.toJson(person);
        System.out.println("json2="+ json2);
        assertFalse(json2.indexOf("85.0") > 0);

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
