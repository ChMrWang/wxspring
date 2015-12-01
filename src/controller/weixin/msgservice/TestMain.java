package controller.weixin.msgservice;

public class TestMain {

	public static void main(String[] args) {

		ApiInvoke ai = new ApiInvokeImpl();
		System.out.println(ai.getWeatherByBaidu("118.9,32.09"));
	}

}
