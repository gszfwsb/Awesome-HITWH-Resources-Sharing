package io;
import java.io.*;
public class Demo21_ByteStremEncrypt {
	/**
	 * @param args
	 * @throws IOException 
	 * ^异或
	 * 将写出的字节异或上一个数,这个数就是密钥,解密的时候再次异或就可以了
	 */
	public static void main(String[] args) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("风景.jpg"));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("copy.jpg"));
		
		int b;
		while((b = bis.read()) != -1) {
			bos.write(b ^ 123);
		}
		
		bis.close();
		bos.close();
	}
}
