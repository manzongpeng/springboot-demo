package com.program.email.common;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * page model
 *
 */

@Data
public class PageModel<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer page;

	private Integer pageSize;

	private Integer totalNum;

	private Integer totalPage;

	private List<T> data;

	public PageModel(Integer page, Integer pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
		this.totalPage = this.totalNum % this.pageSize == 0 ? this.totalNum / this.pageSize
				: this.totalNum / this.pageSize + 1;
	}

}
