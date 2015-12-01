package controller.weixin.access_token;


public class GetToken {
	
	public static void  main(String [] args){
		GettokenBean gettoken = new GettokenBean();
		String token = gettoken.getToken();
		System.out.println(token);
	}
	
	
}
