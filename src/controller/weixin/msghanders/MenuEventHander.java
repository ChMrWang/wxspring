package controller.weixin.msghanders;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import controller.weixin.msgservice.ApiInvoke;
import controller.weixin.msgservice.TextService;

@Service(value = "MenuEventHander")
public class MenuEventHander {
	
	@Resource(name ="apiinvoke")
	private ApiInvoke apiInvoke;
	
	@Resource(name = "locationhander")
	private LocationHander lochand;
	
	@Resource(name = "textservice")
	private TextService textservice;
	
	public String menuEventAdapter(Map<String,String> map){
		
		if(map.get("EventKey").equals("GetWeather"))
			return weatherEventHander(map);
		return null;
	}

	private String weatherEventHander(Map<String,String> map){
		String latiAndLongitude = lochand.getLocation(map.get("FromUserName"));
		//return apiInvoke.getWeatherByBaidu(latiAndLongitude);
		if(latiAndLongitude == null){
			return textservice.textAnsByContent(map, "地址尚未录入，"
					+ "请稍等服务器获取您的位置，或者您可以发送 指令：#天气+城市名  来获取天气信息");
		}
		String weather = apiInvoke.getWeatherByBaidu(latiAndLongitude);
		System.out.println(weather);
		String back = apiInvoke.weatherTransform(weather, map);
		return back;
		//System.out.println(wd.getWeather());
		
	}
}
