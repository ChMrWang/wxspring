package controller.weixin.menu;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import controller.weixin.access_token.GettokenBean;



/**
 * @author Mr Wang
 * 设置微信菜单，随系统启动
 */
@WebServlet
public class Menu extends HttpServlet{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7855032851748956042L;
	
	
	private final static String URL_PATH = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			System.out.println("设置菜单");
			//获取菜单配置文件
			File file = new File(config.getServletContext().getRealPath("media" + File.separator + "menu.json"));
			//System.out.println("---------当前路径----------"+file.getAbsolutePath());			
			
			//向微信服务器调用创建开始菜单接口
			ServletContext application;     
	        WebApplicationContext wac;     
	        application = config.getServletContext();   
	        wac = WebApplicationContextUtils.getWebApplicationContext(application);//获取spring的context 
			
	        GettokenBean gettoken = (GettokenBean)wac.getBean("getAccessToken");  //调用springIOC的bean
						
			String token = gettoken.getLocalToken();
			String url = URL_PATH.replace("ACCESS_TOKEN", token);
			System.out.println(url);
			Request req = Request.Post(url);
			req.bodyFile(file,ContentType.APPLICATION_JSON); //设置post内容为json
			
			String back = req.execute().returnContent().asString(Charset.forName("utf-8"));
			
			System.out.println("修改结果"+back);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}	
