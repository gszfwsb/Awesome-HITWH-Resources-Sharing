package io;

import java.io.*;
/**
 * 小数组拷贝文本
 * 
 * 注意到FileOutputStream的write方法，需要加上偏移量
 * write(byte[] b) 没有设置便宜
 * write(byte[] b, int off, int len)写出有效的字节个数
 */
public class Demo11_CopyTxtUseSmallArrayModi {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("xxx.txt");
		FileOutputStream fos = new FileOutputStream("yyy.txt");
		byte[] arr = new byte[2];
		int len;
		while ((len = fis.read(arr)) != -1) {
			fos.write(arr,0,len); 
		}
		fis.close();
		fos.close();
	}
}
