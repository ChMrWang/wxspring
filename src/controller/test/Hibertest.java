package controller.test;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import test.Userinfo;
import com.entityDao.EntityDao;

/**
 * hibernate测试类，测试增删改查
 * @author Mr Wang
 */
@Controller
@RequestMapping("/hibernate")
public class Hibertest {
	
	@Resource(name = "daoimpl")
	private EntityDao entitydao;
	

	@RequestMapping("/insert")
	public void insert(Userinfo uif){
		entitydao.insert(uif);
		System.out.println("插入数据成功");
	}
	
	@RequestMapping("/delete")
	public void delete(Userinfo uif){
		entitydao.delete(uif);
	}
	
	@RequestMapping("/update")
	public void update(Userinfo uif){
		entitydao.update(uif);
	}
	
	@RequestMapping("/select")
	public void sesect(){
		if(entitydao==null)
			System.out.println("NULLNULL");
		List<?> li = entitydao.select("from userinfo");
		for(Object obj : li){
			Userinfo temp = (Userinfo)obj;
			System.out.println(temp.getUserid()+"\t"+temp.getCategory());
		}
	}
}
