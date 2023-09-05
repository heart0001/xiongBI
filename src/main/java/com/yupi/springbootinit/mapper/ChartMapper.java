package com.yupi.springbootinit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.springbootinit.model.entity.Chart;

import java.util.List;
import java.util.Map;

/**
* @author heart000_1
* @description 针对表【chart(图标信息表)】的数据库操作Mapper
* @createDate 2023-08-09 19:54:51
* @Entity generator.domain.Chart
*/
public interface ChartMapper extends BaseMapper<Chart> {

    List<Map<String, Object>> queryChartData(String querySql);
}




