package controller.weixin.msgservice;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.thoughtworks.xstream.XStream;

import controller.weixin.polo.json.WeatherData;
import controller.weixin.polo.xml.Article;
import controller.weixin.polo.xml.MediaTexXml;

/**
 * @author Mr Wang
 * api调用类
 */
@Service(value="apiinvoke")
public class ApiInvokeImpl implements ApiInvoke {
	
	private final String AK = "DmWTwzkupdVPsyxxuT4zUw91";
	private final String WEATHER_URL = "http://api.map.baidu.com/telematics/v3/weather?location=LOCATION&output=json&ak=AK";

	/* 
	 * @see controller.weixin.msgservice.ApiInvoke#getWeatherByBaidu(java.lang.String)
	 */
	@Override
	public String getWeatherByBaidu(String location) {
		
		String url = WEATHER_URL.replace("LOCATION", location).replace("AK", AK);
		System.out.println(url);
		Request req = Request.Get(url);
		//req.addHeader("User-Agent", "curl");
		String back = null;
		try {
			back = req.execute().returnContent().asString(Charset.forName("utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(back);
		return back;
	}

	@Override
	public String weatherTransform(String weather,Map<String,String>map) {
		System.out.println(weather);
		Gson gson = new Gson();
		JsonParser parse = new JsonParser();
		MediaTexXml mtx = new MediaTexXml();
		mtx.setFromUserName(map.get("ToUserName"));
		mtx.setToUserName(map.get("FromUserName"));
		mtx.setMsgType("news");
		mtx.setCreateTime((int)System.currentTimeMillis());
		List<Article> arts = new ArrayList<Article>();
		mtx.setArticles(arts);
		
		JsonElement je = parse.parse(weather);
		String date = je.getAsJsonObject().get("date").getAsString();
		System.out.println("date:"+date);
		JsonElement result = je.getAsJsonObject().get("results").getAsJsonArray().get(0);
		
		String city = result.getAsJsonObject().get("currentCity").getAsString();
		System.out.println("city:"+city);
		
		String pm25 = result.getAsJsonObject().get("pm25").getAsString();
		System.out.println("pm25:"+pm25);
		JsonArray weather_data = result.getAsJsonObject().get("weather_data").getAsJsonArray();
		System.out.println("第一天"+weather_data.get(0));
		
		Article head = new Article();
		head.setTitle(city+"今天："+"\n"+date+"\t"+"pm2.5:"+pm25);
		head.setDescription("今天："+date+"\t"+"pm2.5:"+pm25);
		head.setPicUrl("http://a.hiphotos.baidu.com/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=a0e988f2d62a283457ab3e593adca28f/a044ad345982b2b71012a12b33adcbef77099b54.jpg");
		arts.add(head);
		mtx.setArticleCount(weather_data.size()+1);
		for(int i = 0;i<weather_data.size();i++){
			WeatherData wd = gson.fromJson(weather_data.get(i), WeatherData.class);
			Article at = new Article();
			at.setTitle(wd.getDate()+"\n"+wd.getTemperature()+"\n"+wd.getWeather()+"\t"+wd.getWind());
			at.setDescription(wd.getTemperature()+"\n"+wd.getWeather()+"\t"+wd.getWind());
			at.setPicUrl(wd.getDayPictureUrl());
			arts.add(at);
		}
		
		XStream xstream = new XStream();
		xstream.alias("xml", mtx.getClass());
		xstream.alias("item", Article.class);
		//System.out.println(xstream.toXML(mtx));
		return xstream.toXML(mtx);
	}

}
