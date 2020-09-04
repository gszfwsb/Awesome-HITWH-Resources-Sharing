package edu.hit.otherio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo32_Data {

	
	public static void main(String[] args) throws IOException {
//		demo1();
//		demo2();
//		demo3();
		demo4();
	}

	public static void demo4() throws FileNotFoundException, IOException {
		DataInputStream dis = new DataInputStream(new FileInputStream("u.txt"));
		int x = dis.readInt();
		int y = dis.readInt();
		int z = dis.readInt();
		
		System.out.println(x);
		System.out.println(y);
		System.out.println(z);
		
		dis.close();
	}

	public static void demo3() throws FileNotFoundException, IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("u.txt"));
		dos.writeInt(997);
		dos.writeInt(998);
		dos.writeInt(999);
		dos.close();
	}
	
	
	/*
	 * 00000000 00000000 00000011 11100101	int类型997
	 * 11100101                                                          写入文件的值
	 * 00000000 00000000 00000000 11100101  demo2读出的值 229
	 */
	public static void demo2() throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream("u.txt");
		int x = fis.read(); //读出来int
		int y = fis.read();
		int z = fis.read();
		
		System.out.println(x);
		System.out.println(y);
		System.out.println(z);
		
		fis.close();
	}

	public static void demo1() throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream("u.txt");
		fos.write(997); //已经超过一个字节，写的时候砍掉前三字节，只留最后一个字节
		fos.write(998); 
		fos.write(999);		
		fos.close();
	}

}
