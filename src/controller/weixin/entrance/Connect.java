package controller.weixin.entrance;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entityDao.EntityDao;

import controller.weixin.msgadapter.MsgHander;

/**
 * 	微信服务器验证 
 *  1. 将token、timestamp、nonce三个参数进行字典序排序
 *  2. 将三个参数字符串拼接成一个字符串进行sha1加密
 *  3.开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
 */
@Controller

public class Connect {
	
	@Resource( name ="daoimpl")
	private EntityDao entitydao;
	
	@Resource( name ="msghander")
	private MsgHander msghander;
	
	private final String TOKEN = "mrwang";
	@RequestMapping(value="/conn",method=RequestMethod.GET)
	public @ResponseBody String bulidConn(String signature,String timestamp,String nonce,String echostr){
		System.out.println(signature);
		System.out.println(timestamp);
		System.out.println(nonce);
		System.out.println(echostr);
		/*if(signature==null||timestamp==null||nonce==null||echostr==null)
			return null;*/
		String[] arr = new String[]{TOKEN,timestamp,nonce};
		Arrays.sort(arr);
		
		StringBuffer temp = new StringBuffer();
		temp.append(arr[0]).append(arr[1]).append(arr[2]);
		
		String sha1sthring = Sha1.getSha1(temp.toString());
		
//		System.out.println(sha1sthring);
		if(sha1sthring.equals(signature)){
//			System.out.println("successful");
			return echostr;
		}
		else 
			return null;
	}
	
	@RequestMapping(value="/conn",method = RequestMethod.POST)
	public void  msgHander(HttpServletRequest request,HttpServletResponse response){
		
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println(msghander.msgAdapter(request));
			//return msghander.msgAdapter(request);
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
		
	}
}
