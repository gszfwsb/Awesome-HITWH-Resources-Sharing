package edu.hit.byteio;

import java.io.FileInputStream;
import java.io.IOException;

public class Demo01_FileInputStreamReadByte {
	
	/**
	 * @param args
	 * @throws IOException  
	/**
	 * read()������ȡ����һ���ֽ�,Ϊ�η�����int,������byte�� 
	 * ��������00010100 00100100 01000001 11111111 0000000
	 * ����11111111�ͻط��أ����º������в��ɶ�
	 * 10000001    byte����-1��ԭ��
	 * 11111110    -1�ķ���
	 * 11111111    -1�Ĳ���
	 * ����������-1�ͱ��������ʽ��
	 * 00000000 00000000 00000000 11111111 int���͵�255
	 * ��read()�������ص�-1 ��int�͵ģ�����
	 * 11111111 11111111 11111111 11111111
	 * ��Ҫע�⣬��ȡ��ÿ���ֽڶ���Ҫ��24��0������Ϊ4���ֽڣ�����ֻ��-1�ż�24��0	 
	 */
	
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("a.txt");
		int x = fis.read(); // ��Ӳ���϶�ȡһ���ֽ�
		System.out.println(x); 
		int y = fis.read();
		System.out.println(y);
		int z = fis.read(); //���ֻ��������ĸ������-1
		System.out.println(z);
		fis.close();
	}
}
