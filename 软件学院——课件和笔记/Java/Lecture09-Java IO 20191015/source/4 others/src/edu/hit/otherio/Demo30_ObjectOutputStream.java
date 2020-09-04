package edu.hit.otherio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import edu.hit.bean.Person;

public class Demo30_ObjectOutputStream {

	/**
	 * @param args
	 * 序列化:将对象写到文件上
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//demo1();
		demo2();
	}

	public static void demo2() throws IOException, FileNotFoundException {
		Person p1 = new Person("张三", 23);
		Person p2 = new Person("李四", 24);
		Person p3 = new Person("王五", 25);
		Person p4 = new Person("赵六", 26);
		
		ArrayList<Person> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("t.txt"));
		oos.writeObject(list);								//把整个集合对象一次写出
		oos.close();
	}

	public static void demo1() throws IOException, FileNotFoundException {
		Person p1 = new Person("张三", 23);
		Person p2 = new Person("李四", 24);		

		//FileOutputStream 无法直接写出对象到文件的。
//		FileOutputStream fos = new FileOutputStream("f.txt");
//		fos.write(p1);		
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("t.txt"));
		
		oos.writeObject(p1);
		oos.writeObject(p2);		
		oos.close();
	}

}
