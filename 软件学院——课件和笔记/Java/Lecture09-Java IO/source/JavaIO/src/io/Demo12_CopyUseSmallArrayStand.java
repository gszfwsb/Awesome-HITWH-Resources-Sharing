package io;

import java.io.*;
/**
 * 小数组拷贝标准写法 
 * 一般在程序中设置的缓冲区大小为1024的偶数倍
 * 工程上以4K，8K，16K等4的倍数为缓冲区小数组的大小
 * 注意fis.read(arr)方法如果漏掉arr，实际上读的fis，此时并没有从小数组读
 * 
 */
public class Demo12_CopyUseSmallArrayStand {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("xxx.txt");
		FileOutputStream fos = new FileOutputStream("yyy.txt");
		byte[] arr = new byte[1024 * 8];
		int len;
		while ((len = fis.read(arr)) != -1) { //如果忘记加arr,返回的就不是读取的字节个数,而是字节的码表值
			fos.write(arr, 0, len); // 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此文件输出流。
		}
		fis.close();
		fos.close();
	}
}
