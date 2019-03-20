package com.fnst.face.common;

import java.util.List;

public class EasyUIResponse<T> {
	private int total;
	private List<T> rows;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getList() {
		return rows;
	}
	public void setList(List<T> list) {
		this.rows = list;
	}
}
