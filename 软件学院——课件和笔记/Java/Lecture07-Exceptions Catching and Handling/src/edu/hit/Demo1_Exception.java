package edu.hit;
public class Demo1_Exception {
	public static void main(String[] args) {
		// demo1();
		Demo d = new Demo();
		int x = d.div(10, 0);
		System.out.println(x);
	}
	public static void demo1() {
		int[] arr = { 11, 22, 33, 44, 55 };
		// arr = null; //NullPointerException 空指针异常
		System.out.println(arr[10]); // ArrayIndexOutOfBoundsException 数组索引越界异常
	}
}
class Demo {
	public int div(int a, int b) { // a = 10,b = 0
		return a / b; // 10 / 0 被除数是10,除数是0当除数是0的时候违背了算数运算法则,抛出异常
						// new ArithmeticException("/ by zero");
	}
}
