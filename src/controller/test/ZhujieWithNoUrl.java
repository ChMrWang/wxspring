package controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/nourl")
public class ZhujieWithNoUrl {
	
	//若RequestMapping内容为空，则默认其访问url为类名的url(/nourl),但为空的RequesMapping只能有一个，若有重复则会发生404错误
	@RequestMapping()
	public ModelAndView mothodOne(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "methodOne");
		mv.setViewName("hello");
		return mv;
	}

	@RequestMapping("/two")
	public ModelAndView mothodTwo() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "methodTwo");
		mv.setViewName("hello");
		return mv;
	}
}
