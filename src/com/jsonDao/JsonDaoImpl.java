package com.jsonDao;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository(value="jsonimpl")
public class JsonDaoImpl implements JsonDao{
	
	@Override
	public String toJson(Object obj) 
	{
		Class<?> classtype = obj.getClass();
		Field[] fields = classtype.getDeclaredFields();
		StringBuffer json = new StringBuffer();
		json.append('{');
		for (Field temp : fields) {
			temp.setAccessible(true);
			Object value = null;
			try {
				value = temp.get(obj);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			json.append('"');
			json.append(temp.getName());
			json.append('"');
			json.append(':');
			json.append('"');
			json.append(value);
			json.append('"');
			json.append(",");
		}
		json.setLength(json.length() - 1);
		json.append('}');
		return json.toString();
	}

	@Override
	public String toJsonArray(List<Object> objs)
	{
		StringBuffer json = new StringBuffer();
		json.append('[');
		for(Object obj:objs){
			json.append(toJson(obj));
			json.append(',');
		}
		json.setLength(json.length()-1);
		json.append(']');
		//System.out.println(json.toString());
		return json.toString();
	}
	
	@Override
	public String toJsonArray(Object [] objs)
	{
		StringBuffer json = new StringBuffer();
		json.append('[');
		for(Object obj:objs){
			json.append(toJson(obj));
			json.append(',');
		}
		json.setLength(json.length()-1);
		json.append(']');
		//System.out.println(json.toString());
		return json.toString();
	}

	@Override
	public Map<String,String> toMap(String json) {
		// TODO Auto-generated method stub
		String[] jsons = json.split("[,{}]");
		Map<String,String> map = new HashMap<String,String>();
		for(int i =0;i<jsons.length;i++){
			if(jsons[i].equals(""))
				continue;
			jsons[i] = jsons[i].replaceAll("\"", "");
			String[] temp =jsons[i].split(":");
			map.put(temp[0], temp[1]);
			
		}
		return map;
	}
}
