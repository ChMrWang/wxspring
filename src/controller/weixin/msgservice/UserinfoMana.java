package controller.weixin.msgservice;


import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.entity.Userinfo;

/**
 * @author Mr Wang
 * 获取用户信息的接口，会将其存入数据库
 */
public interface UserinfoMana {
	
	/**
	 * 拉取用户信息
	 * @param map
	 * @return com.entity.Userinfo
	 */
	public Userinfo pullUserinfo(String openid) throws ClientProtocolException, IOException ;
	
	/**
	 * 从数据库删除一个用户信息
	 * @param openid
	 */
	public void dropUserinfo(String openid);
	
	/**
	 * 将某个用户的关注属性置为0(即未订阅)
	 * @param openid
	 */
	public void unsubscribeUser(String openid);
	
}
