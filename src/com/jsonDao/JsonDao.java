package com.jsonDao;

import java.util.List;
import java.util.Map;

public interface JsonDao {
	public String toJson (Object obj);
	public String toJsonArray (Object [] objs);
	public String toJsonArray(List<Object> objs);
	public Map<String,String> toMap (String Json);
}
