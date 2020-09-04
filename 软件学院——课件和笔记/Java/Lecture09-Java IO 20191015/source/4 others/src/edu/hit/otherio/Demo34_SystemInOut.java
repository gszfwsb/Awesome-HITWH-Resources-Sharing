package edu.hit.otherio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class Demo34_SystemInOut {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		demo1();
		demo2();
		
	}

	public static void demo2() throws FileNotFoundException, IOException {
		System.setIn(new FileInputStream("m.txt"));			//改变标准输入流
		System.setOut(new PrintStream("w.txt"));			//改变标注输出流
		
		InputStream is = System.in;							//获取标准的键盘输入流,默认指向键盘,改变后指向文件
		PrintStream ps = System.out;						//获取标准输出流,默认指向的是控制台,改变后就指向文件
		
		int b;
		while((b = is.read()) != -1) {
			ps.write(b);
		}
		//System.out.println();								//也是一个输出流,不用关,因为没有和硬盘上的文件产生关联的管道
		is.close();
		ps.close();
	}

	public static void demo1() throws IOException {
		InputStream is = System.in;
		int x = is.read(); //从键盘只读入一个字节，所以取第一个字符，更多的使用scanner封装一下
		System.out.println(x);		
		//is.close();		//判断是否需要close，如果关闭，下面的语句没有办法执行；但是和文件关联了必须关闭
		
		InputStream is2 = System.in;
		int y = is2.read();
		System.out.println(y); //发现尽管不同的inputstream流对象，但是也会报错，说明System.in是唯一的
		
	}

}
