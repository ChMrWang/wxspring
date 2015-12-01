package com.jsonDao;

import java.util.Map;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String json = "{\"access_token\":\"pw7vLw3W2ButbIKROthEBjlMn4F6cDX0rLxENw709xSIK2fzkU7pniUsIMpYkPpi_3Qq_YUamJZQHdBNHRpX-ld7dULuScyNzr31H58uQd8\",\"expires_in\":7200}";
		JsonDaoImpl jdm = new JsonDaoImpl();
		Map<String,String> map = jdm.toMap(json);
		System.out.println(map.get("access_token"));
	}

}
