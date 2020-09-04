package edu.hit;

public class Demo5_Exception {
	public static void main(String[] args) {
		int a = 10;
		int b = 0;
		int[] arr = { 11, 22, 33, 44, 55 };
		// JDK7如何处理多个异常
		try {
			System.out.println(a / b);
			System.out.println(arr[10]);
		} catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
			System.out.println("出错了");
		}
	}
}
