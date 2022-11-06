package com.wt.commons.validation;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Will.WT
 * @date 2022/11/04 20:55
 */
public class UserTmp {

    @NotNull(message = "userId required")
    private Long userId;

    @Length(min = 2, max = 32, message = "name length over limit")
    @NotBlank(message = "name required")
    private String name;

    @Range(min = 0, max = 150, message = "age range is [0, 150]")
    private Integer age;

    @Range(min = 0, max = 1, message = "gender range is [0,1]")
    private Integer gender;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserTmp{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
