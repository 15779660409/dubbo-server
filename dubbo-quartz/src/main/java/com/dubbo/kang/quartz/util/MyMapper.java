package com.dubbo.kang.quartz.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的BaseMapper
 *
 * @author sf
 * @since 2017-11-20 21:53
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
    // 该接口不能被扫描到，否则会出错
}
