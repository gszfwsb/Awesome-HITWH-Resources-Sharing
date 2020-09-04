package edu.hit.otherio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import edu.hit.bean.Person;

public class Demo31_ObjectInputStream {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 * ObjectInputStream
	 * 对象输入流,反序列化
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		//demo1();
		demo2();
	}

	public static void demo2() throws IOException, FileNotFoundException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("t.txt"));
		ArrayList<Person> list = (ArrayList<Person>) ois.readObject();		//将集合对象一次读取		
		for (Person person : list) {
			System.out.println(person);
		}		
		ois.close();
	}

	public static void demo1() throws IOException, FileNotFoundException,
			ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("t.txt"));
		
		Person p1 = (Person) ois.readObject();
		Person p2 = (Person) ois.readObject();
		//Person p3 = (Person) ois.readObject();//当文件读取到了末尾时出现EOFException
		
		System.out.println(p1);
		System.out.println(p2);		
		ois.close();
	}

}
