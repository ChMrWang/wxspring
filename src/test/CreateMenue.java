package test;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;




public class CreateMenue {
	
	public static void cremenue() throws ClientProtocolException, IOException{
		String actoken="s1tgadzRg_sG2krUeHOX9k5aTF3rkJGO03JKqveGmZwb9-22CMGcSkOad8oqR0PSpwaOw26q1-I4d52ZHTRxrr_uqUp0NxrXDriPoZ3mkBs";
		System.out.println(actoken);
		HttpClient httpclient=HttpClients.createDefault();
		HttpPost post=new HttpPost("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+actoken);
		//创建menu JSONObject对象
		JSONObject menu = new JSONObject();
		JSONArray menulist = new JSONArray();
		
		JSONObject item = new JSONObject();
		item.put("name", "更多功能");
		item.put("key", "help");
		item.put("type", "click");
		menulist.add(item);
		
		JSONObject item2 = new JSONObject();
		item2.put("name", "QQ空间");
		item2.put("url", "http://user.qzone.qq.com/738589946/infocenter?ptsig=0DatGIwo-Ik0x5G5EBKXO1Hda2-y1p5nYg074Tj*dnI_");
		item2.put("type", "view");
		menulist.add(item2);
		
		menu.put("button", menulist);
		String json = menu.toString();
		System.out.println(URLEncoder.encode(json,"ascII"));
		String json2= URLDecoder.decode(json,"unicode");
		System.out.println(json2);
		StringEntity entity = new StringEntity(menu.toString(),"utf-8");
		post.setEntity(entity);

		HttpResponse response=httpclient.execute(post);
		HttpEntity res = response.getEntity();
		System.out.println(EntityUtils.toString(res));
	}
	@Test
	public void test() throws ClientProtocolException, IOException{
		cremenue();
	}
	
	public static void main(String [] args){
		try {
			cremenue();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
