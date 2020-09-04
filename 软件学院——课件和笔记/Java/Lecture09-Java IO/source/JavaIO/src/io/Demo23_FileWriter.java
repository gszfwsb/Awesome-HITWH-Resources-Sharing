package io;
import java.io.FileWriter;
import java.io.IOException;
/**
 * FileWriter写
 * FileWriter类的write()方法可以自动把字符转为字节写出 *
 */
public class Demo23_FileWriter {
	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("yyy.txt");
		fw.write("大家好!");
		fw.write(97);
		fw.close();
	}
}
