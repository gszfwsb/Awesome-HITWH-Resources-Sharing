package io;

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
     * BufferedWriter的newLine()可以输出一个跨平台的换行符号"\r\n"
	 */
	public static void main(String[] args) throws IOException {
		demo1();
		demo2();
	}

	public static void demo2() throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("zzz.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("aaa.txt"));
		
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
		BufferedReader br = new BufferedReader(new FileReader("zzz.txt"));
		String line;		
		while((line = br.readLine()) != null) {
			System.out.println(line);
		}		
		br.close();
	}
}
