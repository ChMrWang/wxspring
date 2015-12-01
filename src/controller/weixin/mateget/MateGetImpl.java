package controller.weixin.mateget;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;



@Service(value="mateget")
public class MateGetImpl implements MateGet {
	
	//获取临时素材url
	private final String URL_PATH_GET = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	//新增临时素材url
	private final String URL_PATH_PUT = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

	@Override
	public int pictureGet(String token,String mediaid) throws ClientProtocolException, IOException{
				
		String url = URL_PATH_GET.replace("ACCESS_TOKEN", token).replace("MEDIA_ID", mediaid);
		Request req = Request.Get(url);
		Response res = req.execute();
		Content con = res.returnContent();
		//Header type = res.returnResponse().getFirstHeader("Content-Type");
		InputStream in = con.asStream();
		FileOutputStream out = new FileOutputStream("F://wx_test.jpg");
		byte [] data = new byte[1000];
		int len =0;
		while((len = in.read(data))!= -1){
			out.write(data,0,len);
		}
		out.close();
		in.close();
		return 0;
	}

	@Override
	public String picturePut(String token, String filepath,String type) throws ClientProtocolException, IOException {

		File file = new File (filepath);
		
		String uri = URL_PATH_PUT.replace("ACCESS_TOKEN", token).replace("TYPE", type);
		Request req = Request.Post(uri);
		
		req.bodyStream(new FileInputStream(file), ContentType.MULTIPART_FORM_DATA);
		req.addHeader("filename", file.getName());
		req.addHeader("filelength", String.valueOf(file.length()));
		
		Response res = req.execute();
		String returnjson = res.returnContent().asString(Charset.forName("utf-8"));
		System.out.println(returnjson);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(returnjson);
		
		System.out.println(rootNode.asText());
		String mediaId = rootNode.path("media_id").asText();
		
		return mediaId;
	}
}	
