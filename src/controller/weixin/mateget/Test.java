package controller.weixin.mateget;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class Test {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		String token = "-V7AKWAe7sF6mSTpD-HXjpsvpYO5jNhfYceiJ5QebTSPV4c2rEfLa_gxG86Ick735pr7hWGUcI7oL7hG9XetieLyseHi7LKucCAGFeuepyM";
		
		//String mediaid = "Ugs0xDeZcFy4upijV3lwXopsiNRtndlcJxg8zfgkaKw17-RTZnOE83nKpfmph_3q";
		MateGetImpl mg = new MateGetImpl();
		//mg.pictureGet(token, mediaid);
		String mediaId = mg.picturePut(token, "F://wx_test.jpg", MateGet.IMAGE);
		System.out.println(mediaId);
	}

}
