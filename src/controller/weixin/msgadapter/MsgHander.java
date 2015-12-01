package controller.weixin.msgadapter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;

import controller.weixin.msghanders.ImageHander;
import controller.weixin.msghanders.LocationHander;
import controller.weixin.msghanders.MenuEventHander;
import controller.weixin.msghanders.SubscribeEvent;
import controller.weixin.msghanders.TextHander;
import controller.weixin.msgutils.MsgUtil;

@Service(value = "msghander")
public class MsgHander {

	@Resource(name = "msgutil")
	private MsgUtil msgutil;

	@Resource(name = "texthander")
	private TextHander texthander;

	@Resource(name = "imagehander")
	private ImageHander imagehander;
	
	@Resource(name = "locationhander")
	private LocationHander lochander;
	
	@Resource(name = "subscribe")
	private SubscribeEvent subeven;

	@Resource(name = "MenuEventHander")
	private MenuEventHander menuhander;
	public String msgAdapter(HttpServletRequest request) throws IOException, DocumentException {

		Map<String, String> map = msgutil.xmltoMap(request);
		Set<String> keys = map.keySet();
		
		System.out.println("--------------收到信息内容------");
		for (String key : keys) {
			System.out.println(key + ":" + map.get(key));
		}
		System.out.println("-----------------------------");
		
		String type = map.get("MsgType");
		String xml = null;
		switch (type) {
			case "text": {
				xml = texthander.textHand(map);
				break;
			}
			case "image": {
				xml = imagehander.imageHander(map);
				break;
			}
			case "event": {
				String event = map.get("Event");
				switch (event) {
					case "LOCATION": {

						xml = lochander.locationHander(map);
						break;
					}
					case "subscribe":{
						xml = subeven.subscribe(map);
						break;
					}
					case "unsubscribe":{
						subeven.unbscribe(map);
						break;
					}
					case "CLICK" :{
						xml = menuhander.menuEventAdapter(map);
					}
					case "VIEW":{
						
					}
				}
			}
		}
		
		System.out.println("--------回复信息内容---------");
		System.out.println(xml);
		System.out.println("---------------------------");
		return xml;
	}
}
