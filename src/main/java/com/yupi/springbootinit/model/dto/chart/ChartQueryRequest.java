package com.yupi.springbootinit.model.dto.chart;

import com.yupi.springbootinit.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
/**
 * 查询请求
 *
 * @author heart
 * @from heart000_1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChartQueryRequest extends PageRequest implements Serializable {

    /**
     * 图表名称
     */
    private String name;


    /**
     * id
     */
    private Long id;

    /**
     * 分析目标
     */
    private String goal;

    /**
     * 图表类型
     */
    private String chartType;

    /**
     * 用户 id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}