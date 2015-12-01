package controller.weixin.msghanders;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.Userinfo;
import com.entityDao.EntityDao;

import controller.weixin.msgservice.TextService;
import controller.weixin.msgservice.UserinfoMana;

/**
 * @author Mr Wang
 * 关注和解绑的事件类型处理
 */
@Service(value="subscribe")
public class SubscribeEvent {
	
	@Resource(name="daoimpl")
	private EntityDao daoimpl;
	
	@Resource(name ="userinfomana")
	private UserinfoMana usermana;
	
	@Resource(name = "textservice")
	private TextService textserv;
	
	public String subscribe(Map<String,String> map){
		
		String openid = map.get("FromUserName");
		System.out.println("openid:"+openid);
		Userinfo uif =  null;
		try {
			uif = usermana.pullUserinfo(openid);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		daoimpl.insert(uif);  //用户信息入库
		
		return textserv.textAnsByContent(map, "欢迎关注");
	}
	
	public void unbscribe(Map<String,String> map){
		
		String openid  = map.get("FromUserName");
		usermana.dropUserinfo(openid);
	}
}
