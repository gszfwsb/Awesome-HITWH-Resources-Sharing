package edu.hit;

public class Demo3_Exception {
	public static void main(String[] args) {
		Demo2 d = new Demo2();
		try {
			int x = d.div(10, 0);
			System.out.println(x);
		} catch (ArithmeticException a) { // ArithmeticException a = new ArithmeticException();
			System.out.println("³ö´í,³ýÊýÎªÁã");
		}
		System.out.println("1111111");
	}
}

class Demo2 {
	public int div(int a, int b) {
		return a / b;
	}
}
