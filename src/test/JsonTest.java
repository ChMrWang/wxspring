package test;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

class JsonDemo{
	private int id;
	private String name;
	private int [] arr;
	public JsonDemo(int id,String name,int [] arr){
		this.id = id;
		this.name = name;
		this.arr = arr;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int[] getArr() {
		return arr;
	}
	public void setArr(int[] arr) {
		this.arr = arr;
	}
}

public class JsonTest {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {

		Userinfo uif = new Userinfo();
		uif.setCategory("stu");
		uif.setUserid("wangyang");
		uif.setPassword("123");
		uif.setStatus("active");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(uif);
		System.out.println(json);
		
		JsonDemo demo = new JsonDemo(01,"dem0",new int[] {1,2,3});
		json = mapper.writeValueAsString(demo);
		System.out.println(json);
	}

}
