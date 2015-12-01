package controller.weixin.polo.json;
/*package controller.weixin.json.polo;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

public class index {
private String title;

private String zs;

private String tipt;

private String des;

public void setTitle(String title){
this.title = title;
}

public String getTitle(){
return this.title;
}

public void setZs(String zs){
this.zs = zs;
}

public String getZs(){
return this.zs;
}

public void setTipt(String tipt){
this.tipt = tipt;
}

public String getTipt(){
return this.tipt;
}

public void setDes(String des){
this.des = des;
}

public String getDes(){
return this.des;
}

public static index fill(JSONObject jo){
index o = new index();
if (jo.containsKey("title")) {
o.setTitle(jo.getString("title"));
}
if (jo.containsKey("zs")) {
o.setZs(jo.getString("zs"));
}
if (jo.containsKey("tipt")) {
o.setTipt(jo.getString("tipt"));
}
if (jo.containsKey("des")) {
o.setDes(jo.getString("des"));
}
return o;
}

public static List<Index> fillList(JSONArray ja) {
if (ja == null || ja.size() == 0)
return null;
List<Index> sqs = new ArrayList<Index>();
for (int i = 0; i < ja.size(); i++) {
sqs.add(fill(ja.getJSONObject(i)));
}
return sqs;
}

}

package controller.weixin.json.polo;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

public class weather_data {
	private String date;

	private String dayPictureUrl;

	private String nightPictureUrl;

	private String weather;

	private String wind;

	private String temperature;

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return this.date;
	}

	public void setDayPictureUrl(String dayPictureUrl) {
		this.dayPictureUrl = dayPictureUrl;
	}

	public String getDayPictureUrl() {
		return this.dayPictureUrl;
	}

	public void setNightPictureUrl(String nightPictureUrl) {
		this.nightPictureUrl = nightPictureUrl;
	}

	public String getNightPictureUrl() {
		return this.nightPictureUrl;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getWeather() {
		return this.weather;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getWind() {
		return this.wind;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getTemperature() {
		return this.temperature;
	}

	public static weather_data fill(JSONObject jo) {
		weather_data o = new weather_data();
		if (jo.containsKey("date")) {
			o.setDate(jo.getString("date"));
		}
		if (jo.containsKey("dayPictureUrl")) {
			o.setDayPictureUrl(jo.getString("dayPictureUrl"));
		}
		if (jo.containsKey("nightPictureUrl")) {
			o.setNightPictureUrl(jo.getString("nightPictureUrl"));
		}
		if (jo.containsKey("weather")) {
			o.setWeather(jo.getString("weather"));
		}
		if (jo.containsKey("wind")) {
			o.setWind(jo.getString("wind"));
		}
		if (jo.containsKey("temperature")) {
			o.setTemperature(jo.getString("temperature"));
		}
		return o;
	}

	public static List<Weather_data> fillList(JSONArray ja) {
		if (ja == null || ja.size() == 0)
			return null;
		List<Weather_data> sqs = new ArrayList<Weather_data>();
		for (int i = 0; i < ja.size(); i++) {
			sqs.add(fill(ja.getJSONObject(i)));
		}
		return sqs;
	}

}

package controller.weixin.json.polo;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import java.util.List;

public class results {
	private String currentCity;

	private String pm25;

	private List<index> indexs;

	private List<weather_data> weather_datas;

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public String getCurrentCity() {
		return this.currentCity;
	}

	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}

	public String getPm25() {
		return this.pm25;
	}

	public void setIndex(List<index> index) {
		this.indexs = index;
	}

	public List<index> getIndex() {
		return this.indexs;
	}

	public void setWeather_data(List<weather_data> weather_data) {
		this.weather_datas = weather_data;
	}

	public List<weather_data> getWeather_data() {
		return this.weather_datas;
	}

	public static results fill(JSONObject jo) {
		results o = new results();
		if (jo.containsKey("currentCity")) {
			o.setCurrentCity(jo.getString("currentCity"));
		}
		if (jo.containsKey("pm25")) {
			o.setPm25(jo.getString("pm25"));
		}
		if (jo.containsKey("index")) {
			o.setIndex(jo.getJSONArray("index"));
		}
		if (jo.containsKey("weather_data")) {
			o.setWeather_data(jo.getJSONArray("weather_data"));
		}
		return o;
	}

	public static List<Results> fillList(JSONArray ja) {
		if (ja == null || ja.size() == 0)
			return null;
		List<Results> sqs = new ArrayList<Results>();
		for (int i = 0; i < ja.size(); i++) {
			sqs.add(fill(ja.getJSONObject(i)));
		}
		return sqs;
	}

}

package controller.weixin.json.polo;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import java.util.List;

public class Root {
	private int error;

	private String status;

	private String date;

	private List<results> resultss;

	public void setError(int error) {
		this.error = error;
	}

	public int getError() {
		return this.error;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return this.date;
	}

	public void setResults(List<results> results) {
		this.resultss = results;
	}

	public List<results> getResults() {
		return this.resultss;
	}

	public static Root fill(JSONObject jo) {
		Root o = new Root();
		if (jo.containsKey("error")) {
			o.setError(jo.getInt("error"));
		}
		if (jo.containsKey("status")) {
			o.setStatus(jo.getString("status"));
		}
		if (jo.containsKey("date")) {
			o.setDate(jo.getString("date"));
		}
		if (jo.containsKey("results")) {
			o.setResults(jo.getJSONArray("results"));
		}
		return o;
	}

	public static List<Root> fillList(JSONArray ja) {
		if (ja == null || ja.size() == 0)
			return null;
		List<Root> sqs = new ArrayList<Root>();
		for (int i = 0; i < ja.size(); i++) {
			sqs.add(fill(ja.getJSONObject(i)));
		}
		return sqs;
	}

}
*/