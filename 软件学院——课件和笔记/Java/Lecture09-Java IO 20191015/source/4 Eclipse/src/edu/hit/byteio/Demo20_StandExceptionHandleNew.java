package edu.hit.byteio;

import java.io.*;

/**
 * jdk1.7以后的标准异常写法 AutoCloseable
 * FileInputStream，FileOutputStream实现了AutoCloseable
 */
public class Demo20_StandExceptionHandleNew {
	public static void main(String[] args) throws IOException {
		try (FileInputStream fis = new FileInputStream("1.mp3");
				FileOutputStream fos = new FileOutputStream("1copy.mp3");) {
			int b;
			while ((b = fis.read()) != -1) {
				fos.write(b);
			}
		}
	}
}

class MyClose implements AutoCloseable {
	public void close() {
		System.out.println("我关了");
	}
}