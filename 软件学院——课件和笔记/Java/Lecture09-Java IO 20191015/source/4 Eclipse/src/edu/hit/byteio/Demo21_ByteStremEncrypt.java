package edu.hit.byteio;

import java.io.*;

public class Demo21_ByteStremEncrypt {
	/**
	 * ^异或 将写出的字节异或上一个数,这个数就是密钥,解密的时候再次异或就可以了
	 */
	public static void main(String[] args) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("1.jpg"));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("1copy.jpg"));

		int b;
		while ((b = bis.read()) != -1) {
			bos.write(b ^ 123);
		}

		bis.close();
		bos.close();
	}
}
