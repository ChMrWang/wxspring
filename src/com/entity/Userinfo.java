package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * subscribe 		用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。 
 * openid 			用户的标识，对当前公众号唯一
 * nickname 		用户的昵称 
 * sex 				用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 
 * city 			用户所在城市
 * country 			用户所在国家
 * province 		用户所在省份 
 * language 		用户的语言，简体中文为zh_CN 
 * headimgurl		用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
 * subscribe_time 	用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间 
 * unionid			只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制） 
 * remark			公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
 * groupid	 		用户所在的分组ID
 * 
 * @author Mr Wang
 */
@Entity(name="userinfo")
public class Userinfo {
	
	@Id
	private String openid;           //用户标识，对公众号唯一，主键
	private int subscribe;		  //是否关注，0为未关注
	private String nickname;      //昵称
	private int sex;              //性别
	private String city;		  //城市
	private String country;		  //国家
	private String province;	  //省份
	private String language;      //用户语言，中文简体为zh_CN
	private String headimgurl;    //用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	private int  subscribe_time;  //关注时间，10位时间戳
	private String unionid;       //只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	private String remark;        //公众号对用户的备注
	private String groupid;       //分组id
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public int getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public int getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(int subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	
	
}
