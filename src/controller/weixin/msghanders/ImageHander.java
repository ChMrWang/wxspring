package controller.weixin.msghanders;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;

import controller.weixin.access_token.GettokenBean;
import controller.weixin.mateget.MateGet;
import controller.weixin.msgservice.TextService;


/**
 * @author Mr Wang
 * 图片处理hander
 */
@Service(value="imagehander")
public class ImageHander {
	
	@Resource(name="getAccessToken")
	private GettokenBean gettoken;
	
	@Resource(name = "mateget")
	private MateGet metaget;
	
	@Resource(name = "textservice")
	private TextService textserv;
	
	public String imageHander(Map<String,String> map) throws ClientProtocolException, IOException{
		
		String token = gettoken.getLocalToken();
		String mediaid = map.get("MediaId");
		System.out.println(map.get("MediaId"));
		metaget.pictureGet(token, mediaid);
		return textserv.textAnsByContent(map, "图片已收到");
	}
}
