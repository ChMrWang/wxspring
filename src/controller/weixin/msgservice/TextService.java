package controller.weixin.msgservice;

import java.util.Map;

/**
 * @author Mr Wang
 * Text信息相关接口，可以
 * 	存入数据库，
 * 	根据数据库的映射获取返回信息
 */
public interface TextService {
	public void textSave(Map<String, String> map);
	public String textAnsByMapping(Map<String, String> map);
	public String textAnsByContent(Map<String,String>map,String ans);
}
