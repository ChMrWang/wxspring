package controller.weixin.msghanders;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import controller.weixin.msgservice.ApiInvoke;
import controller.weixin.msgservice.TextServiceImpl;

/**
 * @author Mr Wang 对Text型的文本进行处理（入库，返回回复信息）
 */
@Service(value = "texthander")
public class TextHander {

	@Resource(name = "textservice")
	private TextServiceImpl textserv;

	@Resource(name = "apiinvoke")
	private ApiInvoke apiinvoke;

	public String textHand(Map<String, String> map) {

		String ans = null;
		String cont = map.get("Content");
		if (cont.startsWith("#天气")) {           //文本一#天气开头，表示天气查询
			String city = cont.replaceAll("[(#天气)+ ]",""); //去掉前缀剩下的是城市信息
			String wea = apiinvoke.getWeatherByBaidu(city);
			if (wea.startsWith("{\"error\":-3"))    //查询失败
				ans = textserv.textAnsByContent(map, "查不到这个城市的天气");
			else {
				//调用信息转换接口
				ans = apiinvoke.weatherTransform(wea, map);
			}

		} else {
			textserv.textSave(map);  //存入数据库
			ans = textserv.textAnsByMapping(map);
		}
		return ans;
	}
}
