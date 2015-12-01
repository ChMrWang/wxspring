package controller.weixin.msgservice;

import java.util.Map;

/**
 * @author Mr Wang
 * 接口调用service
 */
public interface ApiInvoke {

	/**
	 * 调用百度接口获取天气信息，返回json字符串
	 */
	public String getWeatherByBaidu(String location);
	/**
	 * 将百度获得的json转化为发给用户的xml
	 * @param weather
	 * @return
	 */
	public String weatherTransform(String weather,Map<String,String>map);

}
