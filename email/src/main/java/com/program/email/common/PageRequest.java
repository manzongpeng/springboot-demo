package com.program.email.common;

import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @description: 分页参数基础类
 **/
@Data
public class PageRequest<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 搜索条件
     */
    private T search;

    /**
     * 页码
     */
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码错误")
    private Integer pageNum;

    /**
     * 每页显示数量
     */
    @NotNull(message = "每页显示数量不能为空")
    @Min(value = 1, message = "每页显示数量错误")
    private Integer pageSize;

    /**
     * 排序
     */
    private String orderBy;

    /**
     * 排序规则 0:升序1:降序
     */
    private Integer orderType;
}
