package io;

import java.io.*;

/**
 * 利用数组拷贝的方式拷贝mp3
 * 采用数组拷贝，一次读入数据源所有的字节
 * 再一次写出所有字节，速度明显快，而且高效
 * 但此种读写文件方式不提倡，因为极易内存溢出
 */
public class Demo07_CopyMp3UseByteArray {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("001.mp3"); 
		FileOutputStream fos = new FileOutputStream("copy.mp3"); 
		// int len = fis.available();
		// System.out.println(len);
		byte[] arr = new byte[fis.available()]; // 创建与文件一样大小的字节数组
		fis.read(arr); // 将文件上的字节读取到内存中
		fos.write(arr); // 将字节数组中的字节数据写到文件上
		fis.close();
		fos.close();
		System.out.println("over");
	}
}
