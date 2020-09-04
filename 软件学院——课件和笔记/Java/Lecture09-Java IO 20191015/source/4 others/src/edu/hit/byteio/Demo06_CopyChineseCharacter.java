package edu.hit.byteio;

import java.io.*;
/** 
 *  如果仅仅是文件拷贝，不通过码表显示，可以直接拷贝字节文件或者是字符文件
 *  需要注意关闭输入流，输出流
 *  如果需要准确拷贝时间，可以使用如下语句
 *  运行之前加上：
 *  long startTime = System.currentTimeMillis();
 *  运行之后加上：
 *  long endTime = System.currentTimeMillis();
 *  float seconds = (endTime - startTime) / 1000F;
 *  System.out.println(Float.toString(seconds) + " seconds.");
 */
public class Demo06_CopyChineseCharacter {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("c1.txt"); 
		FileOutputStream fos = new FileOutputStream("c1copy.txt"); 
		int b;
		while ((b = fis.read()) != -1) { // 在不断的读取每一个字节
			fos.write(b); // 将每一个字节写出
		}
		fis.close(); // 关流释放资源
		fos.close();
		System.out.println("ok"); //ppt解释
	}
}
