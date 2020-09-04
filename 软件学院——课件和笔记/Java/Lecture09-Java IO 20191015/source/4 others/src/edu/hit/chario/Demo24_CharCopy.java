package edu.hit.chario;

import java.io.*;


public class Demo24_CharCopy {

	public static void main(String[] args) throws IOException {
		// demo1();
		// demo2();
		// demo3();
		// demo4();
	}

	/*
	 * BufferedReader的read()方法读取字符时会一次读取若干字符到缓冲区, 然后逐个返回给程序, 降低读取文件的次数, 提高效率
	 * BufferedWriter的write()方法写出字符时会先写到缓冲区, 缓冲区写满时才会写到文件, 降低写文件的次数, 提高效率
	 */
	public static void demo4() throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("m.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("n.txt"));
		int c;
		while ((c = br.read()) != -1) {
			bw.write(c);
		}
		br.close();
		bw.close();
	}

	/*
	 * 不可以拷贝非纯文本的文件 因为在读的时候会将字节转换为字符,在转换过程中,可能找不到对应的字符,就会用?代替,写出的时候会将字符转换成字节写出去,
	 * 如果是?,直接写出,这样写出之后的文件就乱了,看不了了
	 */
	public static void demo3() throws FileNotFoundException, IOException {
		FileReader fr = new FileReader("m.txt");
		FileWriter fw = new FileWriter("n.txt");

		char[] arr = new char[1024];
		int len;
		while ((len = fr.read(arr)) != -1) { // 将文件上的数据读取到字符数组中
			fw.write(arr, 0, len); // 将字符数组中的数据写到文件上
		}

		fr.close();
		fw.close();
	}

	/*
	 * 不可以拷贝非纯文本的文件 因为在读的时候会将字节转换为字符,在转换过程中,可能找不到对应的字符,就会用?代替,写出的时候会将字符转换成字节写出去,
	 * 如果是?,直接写出,这样写出之后的文件就乱了,看不了了
	 */
	public static void demo2() throws FileNotFoundException, IOException {

		FileReader fr = new FileReader("1.jpg");
		FileWriter fw = new FileWriter("1copy.jpg");
		int c;
		while ((c = fr.read()) != -1) {
			fw.write(c);
		}
		fr.close();
		fw.close();
	}

	/*
	 * 字符文件拷贝 Writer类中有一个2k的小缓冲区,如果不关流,就会将内容写到缓冲区里 关流会将缓冲区内容刷新,再关闭
	 */
	public static void demo1() throws FileNotFoundException, IOException {
		FileReader fr = new FileReader("m.txt");
		FileWriter fw = new FileWriter("n.txt");

		int c;
		while ((c = fr.read()) != -1) {
			fw.write(c);
		}

		fr.close();
		fw.close(); // Writer类中有一个2k的小缓冲区,如果不关流,就会将内容写到缓冲区里,关流会将缓冲区内容刷新,再关闭
	}
}
