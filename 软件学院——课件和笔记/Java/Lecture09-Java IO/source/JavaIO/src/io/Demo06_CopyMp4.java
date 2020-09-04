package io;

import java.io.*;
/** 
 *  逐字节拷贝mp4，对比Demo05，蓝光电影采用此种方式拷贝效率极低。
 *  需要注意关闭输入流，输出流
 *  如果需要准确拷贝时间，可以使用如下语句
 *  运行之前加上：
 *  long startTime = System.currentTimeMillis();
 *  运行之后加上：
 *  long endTime = System.currentTimeMillis();
 *  float seconds = (endTime - startTime) / 1000F;
 *  System.out.println(Float.toString(seconds) + " seconds.");
 */
public class Demo06_CopyMp4 {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("American.mp4"); 
		FileOutputStream fos = new FileOutputStream("copy.mp4"); 
		int b;
		while ((b = fis.read()) != -1) { // 在不断的读取每一个字节
			fos.write(b); // 将每一个字节写出
		}
		fis.close(); // 关流释放资源
		fos.close();
	}
}
