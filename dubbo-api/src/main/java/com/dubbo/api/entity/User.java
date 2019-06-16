package com.dubbo.api.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author kanghaijun
 * @create 2019/6/14
 * @describe
 */
@Data
@ToString
public class User implements Serializable {

    private Integer id;

    private String name;
}
