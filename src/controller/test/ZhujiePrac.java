package controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Userinfo;

@Controller
@RequestMapping(value = "/zhujie")
public class ZhujiePrac {

	// 注解表明此方法匹配request方式为GET,且含有参数键值对为“q=raw”,同时请求url与类url(/zhujie)一致的url
	//似乎此处的method可以不加，默认既可以接受get请求也可以接受post请求
	@RequestMapping(params = "q=raw", method = RequestMethod.GET)
	public ModelAndView hello() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "zhujiePrac");
		mv.setViewName("hello");
		return mv;
	}

	// 注解标明url为/zhujie/hell_1(类路径加上方法路径)，方式为GET的请求匹配此方法
	@RequestMapping(value = "/hell_1", method = RequestMethod.GET)
	public ModelAndView howareyou() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "How Are You?");
		mv.setViewName("hello");
		return mv;
	}

	// 请求url为"/zhujie/hell_2"，且
	// 有参数名为"message"（注意此处若不加params="message"会报错与上面的方法路径同名冲突）
	@RequestMapping(value = "/hell_2", params = "message",method=RequestMethod.GET)
	public ModelAndView which(@RequestParam("message") String message) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "形参注解参数名匹配" + message);
		mv.setViewName("hello");
		return mv;
	}

	// 请求url为"/zhujie/hell_2"，且 有参数名为"num"（注意此处若不加params="num"会报错与下面的方法路径同名冲突）
	// 当请求地址既包含num 又包含message，会按代码先后匹配日，即先匹配先在代码中出现的方法
	@RequestMapping(value = "/hell_2", params = "num")
	public ModelAndView which(int num) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "形参int类型匹配" + num);
		mv.setViewName("hello");
		return mv;
	}

	// 请求url为"/hell_3/{message}/{num},这种方式获取值要用@PathVariable("message"),
	//注意在这种方式下不可以直接形参变量名匹配的方式来获取参数值，例如下面被注释掉的实现方式，就会报错"
	@RequestMapping(value = "/hell_3/{message}/{num}")
	public ModelAndView which(@PathVariable("message") String message, @PathVariable("num") int num) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "路径标识匹配/{message}/{num}：" + message + num);
		mv.setViewName("hello");
		return mv;
	}

	/*// 请求url为"/hell_3/{message}/{num},这种方式获取值要用@PathVariable("message")"
	@RequestMapping(value = "/hell_4/{message}/{num}")
	public ModelAndView which1(String message,int num) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "路径标识匹配/{message}/{num}：" + message + num);
		mv.setViewName("hello");
		return mv;
	}*/

	//当函数形参为一个bean类时，springMVC会默认将其构造成所需的对象传给形参
	@RequestMapping(value="/hell_4")
	public ModelAndView which(Userinfo uif){
		ModelAndView mv = new ModelAndView();
		//mv.addObject("message", "默认的反射机制自动转换：：" + uif.getUserid());
		mv.setViewName("hello");
		return mv;
	}
}
