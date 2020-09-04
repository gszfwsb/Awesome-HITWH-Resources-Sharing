package edu.hit.byteio;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 利用FileInputStream中的read方法，读入数据并打印到控制台 
 * 查API read的定义：*
 */
public class Demo02_FileInputStreamAllIn {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("a.txt"); 
		int b;
		while ((b = fis.read()) != -1) {
			System.out.println(b);
		}
		fis.close();//关闭资源
	}
}
