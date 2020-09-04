package edu.hit.chario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Demo25_Buffered {

	/**
	 * @param args
	 * 带缓冲区的流中的特殊方法
     * BufferedReader的readLine()方法可以读取一行字符(不包含换行符号)
     * newLine()与\r\n的区别
	 * newLine()是跨平台的方法
     * BufferedWriter的newLine()可以输出一个跨平台的换行符号"\r\n"
	 */
	public static void main(String[] args) throws IOException {
		demo1();
		demo2();
	}

	public static void demo2() throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("m.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("n.txt"));
		
		String line;
		while((line = br.readLine()) != null) {
			bw.write(line);   //读一行
			bw.newLine();	//写出回车换行符
			bw.write("\r\n");
		}		
		br.close();
		bw.close();
	}

	public static void demo1() throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("m.txt"));
		String line;		
		while((line = br.readLine()) != null) { //注意此处是null，而非-1 String对象
			System.out.println(line);
		}		
		br.close();
	}
}
