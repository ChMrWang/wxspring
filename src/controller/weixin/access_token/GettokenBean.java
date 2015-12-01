package controller.weixin.access_token;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import com.entity.AccessToken;
import com.entityDao.EntityDao;


/**
 * @author Mr Wang
 * 获取AccessToken，获取前会按过期时间的60%判断是否过期
 */
@Service("getAccessToken")
public class GettokenBean {
	
	@Resource(name="daoimpl")
	private EntityDao daoimpl;
	
	private final String appID = "wx9deee1cf090ce2b2"; 
	private final String appsecret = "03c6ef6bce9ff8dbb20d8718fd0baf67";
	
	public String getToken(){
		Request req = Request.Get(
				"https://api.weixin.qq.com/cgi-bin/token?"
				+ "grant_type=client_credential"
				+ "&appid="+appID
				+ "&secret="+appsecret);
		try {
			Response res = req.execute();
			Content content = res.returnContent();
			
			System.out.println("拉取access_token:"+content.asString());
			
			//将json转化成对象
			ObjectMapper mapper = new ObjectMapper();
			AccessToken token = mapper.readValue(content.asString(), AccessToken.class);
			token.setId(1);
			
			daoimpl.update(token);
			
			System.out.println("access_token存入数据库");
			
			return token.getAccess_token();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getLocalToken(){
		
		List<?> li  = daoimpl.select("from AccessToken");
		AccessToken at = (AccessToken)li.get(0);
		long createtime = at.getCreatetime().getTime();
		long overtime = createtime + at.getExpires_in()*600;
		if(overtime < System.currentTimeMillis()){
			System.out.println("数据库token已过期，重新获取...");
			return getToken();
		}
		return at.getAccess_token();
		
	}
}
