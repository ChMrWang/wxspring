package controller.weixin.msgservice;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import com.entity.Userinfo;
import com.entityDao.EntityDao;

import controller.weixin.access_token.GettokenBean;

@Service(value="userinfomana")
public class UserinfoManaImpl implements UserinfoMana {
	
	private final String URL_PATH = "https://api.weixin.qq.com/cgi-bin/user/info?";
	
	@Resource(name="daoimpl")
	private EntityDao entitydao;
	
	@Resource(name="getAccessToken")
	private GettokenBean gettoken;

	@Override
	public Userinfo pullUserinfo(String openid) throws ClientProtocolException, IOException {

		String token = gettoken.getLocalToken();
		//https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
		String url = URL_PATH+"access_token="+token+"&openid="+openid+"&lang=zh_CN";
		
		Request req = Request.Get(url);
		String back = req.execute().returnContent().asString(Charset.forName("utf-8"));
		
		System.out.println("返回的用户数据"+back);
		
		ObjectMapper mapper = new ObjectMapper();
		Userinfo uif = mapper.readValue(back, Userinfo.class);
		
		return uif;
	}

	@Override
	public void dropUserinfo(String openid) {

		Userinfo uif = new Userinfo();
		uif.setOpenid(openid);
		entitydao.delete(uif);
		System.out.println("删除用户信息...");
	}

	@Override
	public void unsubscribeUser(String openid) {
		// TODO Auto-generated method stub

	}

}
