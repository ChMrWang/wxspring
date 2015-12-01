package controller.weixin.msgservice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.TextAns;
import com.entity.XmlText;
import com.entityDao.EntityDao;

import controller.weixin.msgutils.MsgUtilImpl;

/**
 * @author Mr Wang Text信息的处理方法集成类
 */

@Service(value = "textservice")
public class TextServiceImpl implements TextService {

	@Resource(name = "daoimpl")
	private EntityDao entitydao;

	@Resource(name = "msgutil")
	private MsgUtilImpl msgutil;

	@Override
	public void textSave(Map<String, String> map) {
		// 把用户信息转化成XmlText类
		XmlText sxt = new XmlText();
		sxt.setContent(map.get("Content"));
		sxt.setCreateTime(Long.parseLong(map.get("CreateTime")));
		sxt.setFromUserName(map.get("FromUserName"));
		sxt.setMsgId(map.get("MsgId"));
		sxt.setMsgType(map.get("MsgType"));
		sxt.setToUserName(map.get("ToUserName"));

		// 存入数据库
		System.out.println("聊天记录存入数据库");
		entitydao.insert(sxt);
	}

	@Override
	public String textAnsByMapping(Map<String, String> map) {
		// 返回信息
		String receive = map.get("Content");
		XmlText xt = new XmlText();
		xt.instance();
		xt.setToUserName(map.get("FromUserName"));
		xt.setMsgId(map.get("MsgId"));
		// 从数据库都被动返回映射表
		List<?> li = entitydao.select("FROM TextAns WHERE RECEIVE = '" + receive + "'");
		if (li.isEmpty())
			xt.setContent("请发送：你好");
		else {
			TextAns ta = (TextAns) li.get(0);
			xt.setContent(ta.getResponse());
		}

		String xml = msgutil.msgtoXml(xt);

		return xml;
	}

	@Override
	public String textAnsByContent(Map<String,String> map,String ans) {

		XmlText xt = new XmlText();
		xt.instance();
		xt.setContent(ans);
		xt.setToUserName(map.get("FromUserName"));
		String xml = msgutil.msgtoXml(xt);
		return xml;
	}
}
