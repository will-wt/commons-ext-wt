package com.will.commons.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Will.WT
 * @date 2022/10/31 10:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    private Date gmtCreate;
    private Long userId;
    private String name;
    private Integer age;

    private List<String> addressList;
    private Map<String, Object> scoreMap;

    private List<Person> children;

}
