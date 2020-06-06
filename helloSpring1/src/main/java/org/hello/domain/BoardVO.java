package org.hello.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BoardVO {
	
	/*
	 * @Override public String toString() { return "BoardVO [b_no=" + b_no +
	 * ", b_title=" + b_title + ", b_detail=" + b_detail + ", b_writer=" + b_writer
	 * + ", b_date=" + b_date + ", b_count=" + b_count + "]"; }
	 */
	private int b_no;
	private String b_title;
    private String b_detail;
    private String b_writer;
    private Date b_date;
    private int b_count;
    
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public String getB_detail() {
		return b_detail;
	}
	public void setB_detail(String b_detail) {
		this.b_detail = b_detail;
	}
	public String getB_writer() {
		return b_writer;
	}
	public void setB_writer(String b_writer) {
		this.b_writer = b_writer;
	}
	public Date getB_date() {
		return b_date;
	}
	public void setB_date(Date b_date) {
		this.b_date = b_date;
	}
	public int getB_count() {
		return b_count;
	}
	public void setB_count(int b_count) {
		this.b_count = b_count;
	}
	
	
	
	
	


}
