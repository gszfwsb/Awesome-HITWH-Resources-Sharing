package edu.hit.byteio;

import java.io.FileOutputStream;
import java.io.IOException;
/**
 * @param args
 * @throws IOException 
 * FileOutputStream在创建对象的时候是如果没有这个文件会帮我创建出来
 * 如果有这个文件就会先将文件清空，再写入；若续写，需在构造加true
 * 虽然写出的是一个int数, 但是到文件上的是一个字节,会自动去除前三个8位
 */
public class Demo03_FileOutputStreamWriteByte {
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("b.txt");
		fos.write(97); // 自动去除前三个8位
		fos.write(98); // 写出时查码表，转换成对应字符
		fos.write(99);
		fos.write(100);
		fos.close();
	}
}
