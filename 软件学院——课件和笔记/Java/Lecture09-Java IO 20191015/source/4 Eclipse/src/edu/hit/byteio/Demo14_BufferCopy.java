package edu.hit.byteio;
import java.io.*;
/**
* A:缓冲思想
	* 字节流一次读写一个数组的速度明显比一次读写一个字节的速度快很多，
	* 这是加入了数组这样的缓冲区效果，java本身在设计的时候，
	* 也考虑到了这样的设计思想，所以提供了字节缓冲区流
* B.BufferedInputStream
	* BufferedInputStream内置了一个缓冲区(数组)
	* 从BufferedInputStream中读取一个字节时
	* BufferedInputStream会一次性从文件中读取8192个, 存在缓冲区中, 返回给程序一个
	* 程序再次读取时, 就不用找文件了, 直接从缓冲区中获取
	* 直到缓冲区中所有的都被使用过, 才重新从文件中读取8192个
* C.BufferedOutputStream
	* BufferedOutputStream也内置了一个缓冲区(数组)
	* 程序向流中写出字节时, 不会直接写到文件, 先写到缓冲区中
	* 直到缓冲区写满, BufferedOutputStream才会把缓冲区中的数据一次性写到文件里
* D.可以通过断点形式，读取文本文件查看一次性读入及一次性写出。
    * 
 */ 
public class Demo14_BufferCopy {
	public static void main(String[] args) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("1.mp3"));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("1copy.mp3"));
		int b;
		while ((b = bis.read()) != -1) {
			bos.write(b);
		}
		bis.close(); 
		bos.close();
		System.out.println("ok");
	}
}
