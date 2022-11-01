package com.will.commons.json;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Will.WT
 * @date 2022/11/01 16:58
 */
public class BaseJsonTest {

    protected Map<String, Object> testMap;
    protected Person person;
    protected String dateStr = "2022-10-30 12:20:30";


    @Before
    public void initData() throws ParseException {
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
    }

}
