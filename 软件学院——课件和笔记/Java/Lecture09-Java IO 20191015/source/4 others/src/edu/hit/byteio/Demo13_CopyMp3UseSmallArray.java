package edu.hit.byteio;
import java.io.*;
/**
 * 用小数组拷贝，不要一次读入太多的数据，一般8*1024字节
 * 一般在程序中设置的缓冲区大小为1024的偶数倍；
 * 工程上以4K，8K，16K等4的倍数为缓冲区小数组的大小。
*/
public class Demo13_CopyMp3UseSmallArray {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("1.mp3");
		FileOutputStream fos = new FileOutputStream("1copy.mp3");
		byte[] arr = new byte[1024 * 8];
		int len;
		while((len = fis.read(arr)) != -1) {//如果忘记加arr,返回的就不是读取的字节个数,而是字节的码表值
			fos.write(arr,0,len);
		}		
		fis.close();
		fos.close();		
		System.out.println("ok"); // ppt 38解释
	}
}
