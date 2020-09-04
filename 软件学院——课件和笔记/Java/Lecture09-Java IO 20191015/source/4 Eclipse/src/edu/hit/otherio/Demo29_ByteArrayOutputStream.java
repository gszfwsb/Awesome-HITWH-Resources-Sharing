package edu.hit.otherio;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Demo29_ByteArrayOutputStream {

	/**
	 * @param args
	 * ByteArrayOutputStream
	 * 内存输出流
	 * 	 * 
	 * FileInputStream读取中文的时候出现了乱码
	 * 
	 * 以上问题解决方案有两个：
	 * 1,字符流读取
	 * 2,ByteArrayOutputStream
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		demo1();
//		demo2();
	}

	public static void demo2() throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream("s.txt");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();	//在内存中创建了可以增长的内存数组
		
		int b;
		while((b = fis.read()) != -1) {
			baos.write(b);		//将读取到的数据逐个写到内存中
		}
		//方式一：可以调整平台码表
		//byte[] arr = baos.toByteArray();	//将缓冲区的数据全部获取出来,并赋值给arr数组
		//System.out.println(new String(arr));
		
		// baos 是内存数组，不用关闭，使用完之后可以由jvm释放
		
		//方式二：使用平台默认的码表转换。
		System.out.println(baos.toString());	//将缓冲区的内容转换为了字符串,在输出语句中可以省略调用toString方法
		fis.close();
	}

	// FileInputStream读取中文的时候出现了乱码
	public static void demo1() throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream("s.txt");
		byte[] arr = new byte[3];
		int len;
		while((len = fis.read(arr)) != -1) {
			System.out.println(new String(arr,0,len));
		}		
		fis.close();
	}

}
