package controller.weixin.msgutils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.XStream;

/**
 * XML转化为Map
 * 实体Object转化为XML
 * @author Mr Wang
 */

//@Repository(value="msgutil")
@Component("msgutil")
public class MsgUtilImpl implements MsgUtil{
	
	@Override
	public Map<String, String> xmltoMap(HttpServletRequest request) throws IOException, DocumentException{
		
		InputStream in = request.getInputStream();
		
		SAXReader read = new SAXReader();
		
		Document doc = read.read(in);
		Element ele = doc.getRootElement();
		List<?> eles = ele.elements();
		
		Map<String, String> map = new HashMap<>();
		
		for(Object obj:eles){
			ele = (Element)obj;
			map.put(ele.getName(), ele.getText());
		}
		
		in.close();
		return map;
	}
	
	/* 将entity对象转化成xml串，并将根节点改为<xml>
	 * @see controller.weixin.msgutils.MsgUtil#msgtoXml(java.lang.Object)
	 */
	@Override
	public String msgtoXml(Object obj){
		XStream xstream = new XStream();
		xstream.alias("xml", obj.getClass());
		return xstream.toXML(obj);
	}

	@Override
	public Map<String, String> xmltoMap(String xml) throws UnsupportedEncodingException, DocumentException {

		SAXReader read = new SAXReader();
		Document doc = read.read(new ByteArrayInputStream(xml
			     .getBytes("utf-8")));
		Element ele = doc.getRootElement();
		List<?> eles = ele.elements();
		
		Map<String, String> map = new HashMap<>();
		
		for(Object obj:eles){
			ele = (Element)obj;
			map.put(ele.getName(), ele.getText());
		}
		
		return map;
	}
}
