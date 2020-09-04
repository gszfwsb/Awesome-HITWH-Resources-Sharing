package edu.hit.byteio;

import java.io.FileInputStream;
import java.io.IOException;

public class Demo01_FileInputStreamReadByte {
	
	/**
	 * @param args
	 * @throws IOException  
	/**
	 * read()方法读取的是一个字节,为何返回是int,而不是byte？ 
	 * 假设序列00010100 00100100 01000001 11111111 0000000
	 * 读到11111111就回返回，导致后续序列不可读
	 * 10000001    byte类型-1的原码
	 * 11111110    -1的反码
	 * 11111111    -1的补码
	 * 因此如果碰到-1就变成如下形式：
	 * 00000000 00000000 00000000 11111111 int类型的255
	 * 而read()方法返回的-1 是int型的，即：
	 * 11111111 11111111 11111111 11111111
	 * 需要注意，读取的每个字节都需要补24个0，升级为4个字节，并非只是-1才加24个0	 
	 */
	
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("a.txt");
		int x = fis.read(); // 从硬盘上读取一个字节
		System.out.println(x); 
		int y = fis.read();
		System.out.println(y);
		int z = fis.read(); //如果只有两个字母，返回-1
		System.out.println(z);
	}
}
