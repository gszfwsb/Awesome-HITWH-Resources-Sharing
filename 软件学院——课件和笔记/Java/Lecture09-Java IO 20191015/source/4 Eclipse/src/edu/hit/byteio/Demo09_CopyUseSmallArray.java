package edu.hit.byteio;
import java.io.*;
/**
 * 演示小数组拷贝容易出现的问题
 * write(byte[] b)
 * write(byte[] b, int off, int len)写出有效的字节个数
 * 设置xxx.txt为abc
 * 第一次读入值为 ab的ASCII值，即97,98
 * 第二次读入值应该为c的ASCII值，但结果是99,98
 * 原因在于byte[]数组的第二个值并没有被覆盖掉，只读一个c覆盖数组第一个数值
 * 原来第一次读的b保留在数组的末尾
 * 因此，在文件copy过程中，如果直接使用write()方法会出现问题
 */
public class Demo09_CopyUseSmallArray {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("c.txt");//abc
		byte[] arr = new byte[2];
		int a = fis.read(arr);	//将文件上的字节读取到字节数组中		
		System.out.println(a);	//读到的有效字节个数
		for (byte b : arr) {	//第一次获取到文件上的a和b
			System.out.println(b);
		}		
		System.out.println("-----------------------");		
		int c = fis.read(arr);
		System.out.println(c);
		for (byte b : arr) {
			System.out.println(b); //此时输入出现问题
		}
		fis.close(); 
	}
}
