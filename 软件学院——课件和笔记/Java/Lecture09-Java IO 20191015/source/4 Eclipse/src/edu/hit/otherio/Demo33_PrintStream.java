package edu.hit.otherio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import edu.hit.bean.Person;

public class Demo33_PrintStream {

	/**
	 * @param args
	 * @throws IOException 
	 * PrintStream和PrintWriter分别是打印的字节流和字符流
	 * 只操作数据宿
	 */
	public static void main(String[] args) throws IOException {
		demo1();
		//demo2();
	}

	public static void demo2() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new FileOutputStream("v.txt"),true);
		//pw.println(97);//自动刷出功能只针对的是println方法
		//pw.write(97); //查码表得到a，如果忘了关流，是写不出的，有2K缓冲
		pw.print(97); // 忘了关流也不能自动刷出
		pw.println(97); //自动刷出
		pw.close(); 
	}

	public static void demo1() {
		System.out.println("aaa");
		
		
		PrintStream ps = System.out;			//可以换一种方式写：获取标注输出流
		ps.println(97);							//底层通过Integer.toString()将97转换成字符串并打印
		ps.write(97);							//查找码表,找到对应的a并打印		
		Person p1 = new Person("张三", 23);
		ps.println(p1);							//默认调用p1的toString方法		
		Person p2 = null;						//打印引用数据类型,如果是null,就打印null,如果不是null就打印对象的toString方法
		ps.println(p2);
		ps.close();
	}

}
