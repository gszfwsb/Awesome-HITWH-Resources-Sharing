package io;

import java.io.*;
/**
 * 字节流写出中文的问题
	   * 字节流直接操作的字节,所以写出中文必须将字符串转换成字节数组 
	   * 写出回车换行 write("\r\n".getBytes()); 
	   * 可以发现字节流处理中文比较麻烦
 */
public class Demo17_ByteStreamWriteChinese {
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("zzz.txt");
		fos.write("哈尔滨工业大学".getBytes());// to byte[]
		fos.write("\r\n".getBytes());//转义字符也如此处理
		fos.close();
	}
}
