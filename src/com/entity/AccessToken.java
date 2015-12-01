package com.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccessToken {
	@Id
	private int id;
	private  String access_token = 
		"ezJn5-iTKZ03uVcvpMR5r0CcNE4IzLe8-CbA_e8KH2EOb6LnHzmRUpIiHtNZl5fq_ZdQAeZis6nfx6HXCQyKnaYc5bsC7Pcxe5PsGAqve5Q";
	private  int expires_in;
	
	@Column(columnDefinition="timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private  Timestamp createtime; 
	
	public  String getAccess_token() {
		return access_token;
	}
	public  void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public  int getExpires_in() {
		return expires_in;
	}
	public  void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	
}
