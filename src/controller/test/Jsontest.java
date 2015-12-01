package controller.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Userinfo;

@Controller
@RequestMapping(value="/jsontest")
public class Jsontest {

	@RequestMapping(value="/getjson",params="t",method=RequestMethod.POST)
	public void receive(@RequestBody Userinfo uif){
		//System.out.println(uif.getUserid());
	}
	
	@RequestMapping(value="/getinfo")
	public @ResponseBody List<?> send(){
		Userinfo temp = new Userinfo();
		//temp.setUserid("wangyang");
		//temp.setCategory("student");
		List<Userinfo> li  = new ArrayList<>();
		li.add(temp);
		li.add(temp);
		return li;
	}
}
