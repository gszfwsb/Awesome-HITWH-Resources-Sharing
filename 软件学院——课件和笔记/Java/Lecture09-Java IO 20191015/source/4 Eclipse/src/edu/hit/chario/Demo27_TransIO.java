package edu.hit.chario;

import java.io.*;

public class Demo27_TransIO {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		demo1();
//		demo2();
//      demo3();
	}

	public static void demo3() throws UnsupportedEncodingException, FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("utf-8.txt"), "utf-8"));// 更高效的读
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("gbk.txt"), "gbk"));// 更高效的写
		int c;
		while ((c = br.read()) != -1) {
			bw.write(c);
		}

		br.close();
		bw.close();
	}

	//用InputStreamReader转换流进行转换
	public static void demo2() throws IOException {
		InputStreamReader isr = new InputStreamReader(new FileInputStream("utf-8.txt"), "uTf-8"); // 指定码表读字符
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("gbk.txt"), "gbk"); // 指定码表写字符

		int c;
		while ((c = isr.read()) != -1) {
			osw.write(c);
		}

		isr.close();
		osw.close();
	}

	public static void demo1() throws FileNotFoundException, IOException {
		// 用默认编码表读写,出现乱码
		FileReader fr = new FileReader("utf-8.txt");
		FileWriter fw = new FileWriter("gbk.txt");

		int c;
		while ((c = fr.read()) != -1) {
			fw.write(c);
		}

		fr.close();
		fw.close();
	}

}
