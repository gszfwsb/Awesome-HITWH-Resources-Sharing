package edu.hit.otherio;

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

public class Demo28_SequenceInputStream {

	/**
	 * @param args
	 * 整合两个输入流
	 * SequenceInputStream(InputStream s1, InputStream s2)
	 * 整合多个输入流
	 * SequenceInputStream(Enumeration<? extends InputStream> e)
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//demo1();
		//demo2();
		demo3();
	}

	public static void demo3() throws FileNotFoundException, IOException {
		FileInputStream fis1 = new FileInputStream("o.txt");
		FileInputStream fis2 = new FileInputStream("p.txt");
		FileInputStream fis3 = new FileInputStream("q.txt");
		
		Vector<FileInputStream> v = new Vector<>();					//创建集合对象
		v.add(fis1);												//将流对象存储进来
		v.add(fis2);
		v.add(fis3);
		
		Enumeration<FileInputStream> en = v.elements();
		SequenceInputStream sis = new SequenceInputStream(en);		//将枚举中的输入流整合成一个
		FileOutputStream fos = new FileOutputStream("r.txt");
		
		int b;
		while((b = sis.read()) != -1) {
			fos.write(b);
		}
		
		sis.close();
		fos.close();
	}

	//可以把多个流合并成一个流进行处理
	public static void demo2() throws FileNotFoundException, IOException {
		FileInputStream fis1 = new FileInputStream("a.txt");
		FileInputStream fis2 = new FileInputStream("b.txt");
		SequenceInputStream sis = new SequenceInputStream(fis1, fis2);
		FileOutputStream fos = new FileOutputStream("c.txt");
		
		int b;
		while((b = sis.read()) != -1) {
			fos.write(b);
		}
		
		sis.close(); //sis在关闭的时候,会将构造方法中传入的流对象也都关闭
		fos.close();
	}
    //如果不用SequenceInputStream流，需要每个流都单独写
	public static void demo1() throws FileNotFoundException, IOException {
		FileInputStream fis1 = new FileInputStream("a.txt");		//创建字节输入流关联a.txt
		FileOutputStream fos = new FileOutputStream("c.txt");		//创建字节输出流关联c.txt		
		int b1;
		while((b1 = fis1.read()) != -1) {							//不断的在a.txt上读取字节
			fos.write(b1);											//将读到的字节写到c.txt上
		}
		fis1.close();											//关闭字节输入流		
		FileInputStream fis2 = new FileInputStream("b.txt");
		int b2;
		while((b2 = fis2.read()) != -1) {
			fos.write(b2);
		}		
		fis2.close();
		fos.close();
	}
}
