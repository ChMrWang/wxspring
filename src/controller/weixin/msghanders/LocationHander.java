package controller.weixin.msghanders;

import java.text.DateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.Location;
import com.entityDao.EntityDao;

import controller.weixin.msgservice.TextService;

/**
 * @author Mr Wang
 * 收到地理位置信息后进行处理，返回位置
 */

@Service("locationhander")
public class LocationHander {
	
	@Resource(name="daoimpl")
	private EntityDao entitydao;
	
	@Resource(name = "textservice")
	private TextService textserv;
	
	private Map<String,Location> locs;
	
	public LocationHander(){
		locs = new Hashtable<>();
	}
	
	public String locationHander(Map<String,String> map){
		
		Location loc = new Location();
		loc.setOpenID(map.get("FromUserName"));
		loc.setLatitude(Double.parseDouble(map.get("Latitude")));
		loc.setLongitude(Double.parseDouble(map.get("Longitude")));
		loc.setPrec(Double.parseDouble(map.get("Precision")));
		loc.setCreateTime(Integer.parseInt(map.get("CreateTime")));
		
		Location lastloc  = locs.put(loc.getOpenID(), loc);
		
		System.out.println("地理位置条数："+locs.size());
		
		//每隔一分钟存入一条位置记录，数据库中的记录在轨迹查询和后续分析会用，同时会向用户返回一条地理位置做测试之用
		if(lastloc == null||System.currentTimeMillis()-lastloc.getLastinsert()>60*1000){
			entitydao.insert(loc);
			System.out.println("存入数据库@"+DateFormat.getDateInstance().format(new Date()));
			loc.setLastinsert(System.currentTimeMillis());
			StringBuffer sb = new StringBuffer().append("纬度："+loc.getLatitude()).append("  ")
					.append("经度"+loc.getLongitude());
			System.out.println("回送信息："+sb.toString());
			return textserv.textAnsByContent(map, sb.toString());  
		}
		return null;
		
	}
	
	/**
	 * 根据UserID获取当前存储位置map中的用户地址，若地址尚未录入，返回null
	 * @param userid
	 * @return
	 */
	public String getLocation(String userid){
		Location loc = locs.get(userid);
		String locstring = null;
		if(loc!=null)
			locstring = loc.getLongitude()+","+loc.getLatitude();
		return locstring;
	}
}
