package controller.test;

class Path{
	public void getPath(){
		System.out.println(this.getClass().getResource(""));
	}
}

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Path().getPath();
	}

}
