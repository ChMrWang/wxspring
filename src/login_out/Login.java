package login_out;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import dbpackge1_0.DBService;
import test.Userinfo;

public class Login implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		String username = arg0.getParameter("username");
		String pwd = arg0.getParameter("password");
		System.out.println(username);
		System.out.println(pwd);
		List<Object> li = DBService.selectReflect(new Userinfo());
		ModelAndView mv = new ModelAndView();
		boolean exist = false;
		for(Object temp:li)
		{
			Userinfo uif = (Userinfo)temp;
			if(uif.getUserid().equals(username))
			{
				exist = true;
				if(uif.getPassword().equals(pwd)){
					System.out.println("登陆成功");
					mv.addObject("loged", "1");
				}else{
					System.out.println("密码错误");
					mv.addObject("loged", "-1"); //-1表示密码错误
				}
			}
		}
		if(!exist)
			mv.addObject("loged", "-2"); //-2表示用户名错误
		mv.setViewName("index");
		mv.addObject("message", "helloworld");
		return mv;
	}

}

