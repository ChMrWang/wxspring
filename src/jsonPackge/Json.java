package jsonPackge;

import java.lang.reflect.Field;
import java.util.List;

public class Json {
	public static String toJson(Object obj) 
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
			// System.out.println(temp.getName()+" "+temp.getType());
		}
		// 这里多余的一个，没有在字符串中删除
		// json.deleteCharAt(json.length()-1);
		json.setLength(json.length() - 1);
		json.append('}');
		return json.toString();
	}

	public static String toJsonArray(List<Object> objs)
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
	
	public static String toJsonArray(Object [] objs)
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
	
	/*public static Object toObject(String json,String packge)
	{
		Class<?> classtype = Class.forName(packge);
		Object obj = classtype.newInstance();
	}*/
}
