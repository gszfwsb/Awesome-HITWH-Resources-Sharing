package edu.hit;

public class Demo4_Exception {
	public static void main(String[] args) {
		int a = 10;
		int b = 0;
		int[] arr = { 11, 22, 33, 44, 55 };
		try {
			System.out.println(a / b);
			System.out.println(arr[10]);
			int[] s = null;
			arr = s;
			System.out.println(arr[0]);

		} catch (ArithmeticException e) {
			System.out.println("ArithmeticException");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ArrayIndexOutOfBoundsException");
		} catch (Exception e) { // Exception e = new NullPointerException();
			System.out.println("ณ๖ดํมห");
		}
		System.out.println("over");
	}
}
