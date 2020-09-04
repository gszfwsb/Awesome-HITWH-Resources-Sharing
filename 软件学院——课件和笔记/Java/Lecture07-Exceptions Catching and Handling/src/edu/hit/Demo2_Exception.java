package edu.hit;
import java.io.FileInputStream;
public class Demo2_Exception {
	public static void main(String[] args) {
		// 运行时异常：
		int[] arr = { 11, 22, 33, 44, 55 };
		System.out.println(arr[10]); // ArrayIndexOutOfBoundsException 数组索引越界异常
		// 编译时异常
		FileInputStream fis = new FileInputStream("xxx.txt");
	}
}
