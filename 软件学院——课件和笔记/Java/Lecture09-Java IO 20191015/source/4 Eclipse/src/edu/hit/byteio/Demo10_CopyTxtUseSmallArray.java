package edu.hit.byteio;

import java.io.*;
/**
 * 小数组拷贝文本，此程序演示FileOutputStream的write()方法容易出现的问题
 * 如果直接输出，会产生错误 
 * 设置d.txt为abc
 * 第一次读入值为 ab的ASCII值，即97,98
 * 第二次读入值应该为c的ASCII值，但结果是99,98
 * 因此拷贝到yyy.txt必然是abcb，而非期望的abc  
 */

public class Demo10_CopyTxtUseSmallArray {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("c.txt");
		FileOutputStream fos = new FileOutputStream("d.txt");
		byte[] arr = new byte[2];
		int len;
		while ((len = fis.read(arr)) != -1) {
			fos.write(arr);
		}
		fis.close();
		fos.close();
	}  // ppt P33 解释
}
