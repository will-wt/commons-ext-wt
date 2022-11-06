package com.wt.commons.json;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * @author Will.WT
 * @date 2022/11/01 17:03
 */
public class JacksonHelperTest extends BaseJsonTest {

    @Before
    public void init() throws ParseException {
        super.initData();
    }

    @Test
    public void testMap(){
        String json1 = JacksonHelper.toJson(testMap);
        System.out.println("json1="+ json1);
        assertFalse(json1.indexOf("100.0") > 0);

        Map<String, Object> map = JacksonHelper.fromJsonMap(json1);
        assertNotNull(map);
        System.out.println("json1-1="+ GsonHelper.toJson(map));
        assertEquals( 100, map.get("int"));
        assertEquals(100, ((Map)map.get("subMap")).get("int"));
    }

    @Test
    public void testListMap(){
        List<Map<String, Object>> testList = new ArrayList<Map<String, Object>>();
        testList.add(testMap);

        String json1 = JacksonHelper.toJson(testList);
        assertNotNull(json1);
        System.out.println("json1="+ json1);

        List<Map<String, Object>> listTemp = JacksonHelper.fromJsonList(json1);
        assertNotNull(listTemp);
        System.out.println("json2="+ JacksonHelper.toJson(listTemp));

        assertEquals(100, listTemp.get(0).get("int"));
        assertEquals(100, ((Map)listTemp.get(0).get("subMap")).get("int"));
    }

    @Test
    public void testBean(){
        String json2 = JacksonHelper.toJson(person);
        System.out.println("json2="+ json2);
        assertFalse(json2.indexOf("85.0") > 0);

        Person personTemp = JacksonHelper.fromJson(json2, Person.class);
        assertNotNull(personTemp);
        assertNotNull(personTemp.getUserId());
        System.out.println("json2-2="+ GsonHelper.toJson(personTemp));
    }

}
