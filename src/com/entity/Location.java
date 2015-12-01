package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;


/**
 * @author Mr Wang
 * 地理位置实体类
 */
@Entity
public class Location {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int prikey;
	private String OpenID;
	private int CreateTime;
	private double Latitude;      //维度
	private double Longitude;     //经度
	private double Prec;     //精度，Precision是数据库关键字 ，不能用！！！！！
	@Transient
	private long lastinsert;
	public int getPrikey() {
		return prikey;
	}
	public void setPrikey(int prikey) {
		this.prikey = prikey;
	}
	public String getOpenID() {
		return OpenID;
	}
	public void setOpenID(String openID) {
		OpenID = openID;
	}
	public int getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(int createTime) {
		CreateTime = createTime;
	}
	/**
	 * 获取精度
	 * @return double
	 */
	public double getLatitude() {
		return Latitude;
	}
	public void setLatitude(double latitude) {
		Latitude = latitude;
	}
	/**
	 * 获取维度
	 * @return double
	 */
	public double getLongitude() {
		return Longitude;
	}
	public void setLongitude(double longitude) {
		Longitude = longitude;
	}
	public double getPrecision() {
		return Prec;
	}
	public void setPrec(double precision) {
		Prec = precision;
	}
	public long getLastinsert() {
		return lastinsert;
	}
	public void setLastinsert(long lastinsert) {
		this.lastinsert = lastinsert;
	}
	public double getPrec() {
		return Prec;
	}
}
