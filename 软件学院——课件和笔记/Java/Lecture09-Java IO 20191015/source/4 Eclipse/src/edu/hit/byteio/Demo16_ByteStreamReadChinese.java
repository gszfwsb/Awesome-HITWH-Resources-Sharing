package edu.hit.byteio;

import java.io.*;

/**
 * chinese编码是GBK，每个字符是两个字节，而read()方法每次只能读一个字节 所以采用数组形式。读入数组后，将数组内容转成中文。 查api文档
 * String(byte[] bytes) 构造： 功能：通过使用平台的默认字符集解码指定的 byte 数组，构造一个新的 String。
 * 因此可以用String(byte[] bytes)转中文。
 * 
 * 字节流读取中文的问题: 字节流在读中文的时候有可能会读到半个中文,造成乱码 字节流写出中文的问题
 * 字节流直接操作的字节,所以写出中文必须将字符串转换成字节数组 写出回车换行 write("\r\n".getBytes());
 */

public class Demo16_ByteStreamReadChinese {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("chinese.txt");
		// byte[] arr = new byte[3];//出现半个
		byte[] arr = new byte[4]; // 也不能保证，如果文中出现逗号等也会出问题
		int len;
		while ((len = fis.read(arr)) != -1) {
			System.out.println(new String(arr, 0, len));
		}
		fis.close();
	}
}
