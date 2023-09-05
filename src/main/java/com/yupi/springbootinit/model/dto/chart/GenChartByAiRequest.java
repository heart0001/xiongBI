package com.yupi.springbootinit.model.dto.chart;

import lombok.Data;

import java.io.Serializable;

/**
 * 文件上传请求
 *
 * @author heart
 * @from heart000_1
 */
@Data
public class GenChartByAiRequest implements Serializable {

    /**
     * 图表名称
     */
    private String name;

    /**
     * 目标
     */
    private String goal;

    /**
     * 图表类型
     */
    private String chartType;

    private static final long serialVersionUID = 1L;
}