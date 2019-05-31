package com.bootdo.prepareLessons.domain;

import lombok.Data;

import java.io.Serializable;

/**
 *  备课管理类
 */
@Data
public class LessionsDO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;//序号
	private String title;//备课标题
	private Integer catalog;//所属目录
	private Integer term;//学期
	private String creationtime;//创建时间
	private String content;//备课内容

	private String catalogDTO;//所属目录转换
	private String termDTO;//学期转换
}
