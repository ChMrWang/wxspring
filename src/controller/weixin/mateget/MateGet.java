package controller.weixin.mateget;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

/**
 * @author Mr Wang
 * 获取媒体资源（HttpClient）（image）、语音（voice）、视频（video）和缩略图（thumb）
 */
public interface MateGet {
	
	public static final String IMAGE="image";
	public static final String VOICE="voice";
	public static final String VIDEO="video";
	public static final String THUMB="thumb";
	
	/**
	 * 获取图片
	 * @param token
	 * @param mediaid
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public int pictureGet(String token,String mediaid) throws ClientProtocolException, IOException;
	
	/**
	 * 上传图片，返回midiaID
	 * @param accesstoken
	 * @param filepath
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public String picturePut(String accesstoken,String filepath,String type) throws ClientProtocolException, IOException;
}
