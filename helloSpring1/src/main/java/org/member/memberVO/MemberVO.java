package org.member.memberVO;

import java.util.Date;

public class MemberVO {
	
	/*
	userID varchar(30) primary key,
	userPW varchar(100),
	userName varchar(30),
	regDate date,
	*/
	private String userID ;
	private String userPW ;
	private String userName;
	private Date regDate;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPW() {
		return userPW;
	}
	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}
