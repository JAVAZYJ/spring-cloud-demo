package com.bootdo.prepareLessons.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class CatalogDO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;//序号
    private String catalog; //目录名称

    private int lessionsCount;//目录重复统计
}
