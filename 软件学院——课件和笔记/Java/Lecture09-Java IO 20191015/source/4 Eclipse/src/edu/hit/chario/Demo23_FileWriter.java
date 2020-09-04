//package edu.hit.chario;

import java.io.FileWriter;
import java.io.IOException;

/**
 * FileWriter写 FileWriter类的write()方法可以自动把字符转为字节写出 *
 */
public class Demo23_FileWriter {
	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("k.txt");
		fw.write("大家好!");
		fw.write("\r\n");
		fw.write(97);		
		fw.close();
	}
}
