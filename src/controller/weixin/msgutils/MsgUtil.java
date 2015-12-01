package controller.weixin.msgutils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;

/**
 * xml处理接口
 * @author Mr Wang
 *
 */
public interface MsgUtil {
	
	/**
	 * 将request装话为map
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public Map<String,String> xmltoMap(HttpServletRequest request) throws IOException, DocumentException;
	/**
	 * 将xml字符串转化为map
	 * @param xml
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws DocumentException
	 */
	public Map<String,String> xmltoMap(String xml) throws UnsupportedEncodingException, DocumentException;
	/**
	 * 将类转化成xml字符串
	 * @param obj
	 * @return
	 */
	public String msgtoXml(Object obj);
	


}	
